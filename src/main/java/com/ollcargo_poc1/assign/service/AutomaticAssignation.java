package com.ollcargo_poc1.assign.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ollcargo_poc1.assign.model.Assignation;
import com.ollcargo_poc1.assign.model.CollectPoint;
import com.ollcargo_poc1.assign.model.DeliveryPerson;
import com.ollcargo_poc1.assign.model.DeliveryPersonType;
import com.ollcargo_poc1.assign.model.DeliveryPoint;
import com.ollcargo_poc1.assign.model.Geolocation;
import com.ollcargo_poc1.assign.model.Interval;
import com.ollcargo_poc1.assign.model.Order;
import com.ollcargo_poc1.assign.model.Schedule;
import com.ollcargo_poc1.assign.model.Zone;
import com.ollcargo_poc1.assign.repository.AssignationRespository;
import com.ollcargo_poc1.assign.repository.DeliveryPersonRespository;
import com.ollcargo_poc1.assign.repository.OrderRepository;

@Service
public class AutomaticAssignation {
    @Autowired
	private DeliveryPersonRespository deliveryPersonRepository;

    @Autowired
	private OrderRepository orderRespository;

    @Autowired
	private AssignationRespository assignationRepository;
        
    public Assignation automaticPlan() {      
        Order order = orderRespository.findFirstByOrderById();
        List<DeliveryPerson> deliveryPersonsMatched = new ArrayList<DeliveryPerson>();

        // find potential delivery persons to make the delivery
        List<DeliveryPerson> deliveryPersons = deliveryPersonRepository.findAll();
        for (int i = 0; i < deliveryPersons.size(); i++) {
            DeliveryPerson deliveryPerson = deliveryPersons.get(i);
            
            boolean weightCond = order.getWeight() <= deliveryPerson.getMaxWeight();
            boolean fragileCond = order.isFragile() ? deliveryPerson.isCanDeliverFragile() : true;
            boolean scheduleCond = checkSchedule(
                order.getDeliveryTimes(), deliveryPerson.getSchedules());
            boolean zoneCond = false;
            if (deliveryPerson.getType() == DeliveryPersonType.Livreur) {
                zoneCond = checkPickUpZone(order.getCollectPoint(), deliveryPerson.getPickUpZones())
                && checkDropOffZone(order.getDeliveryPoints(),deliveryPerson.getDropOffZones());
            } else if (deliveryPerson.getType() == DeliveryPersonType.VendeurLivreur) {
                zoneCond = checkPickUpPoint(order.getCollectPoint(), deliveryPerson.getShop())
                    && checkDropOffZone(order.getDeliveryPoints(),deliveryPerson.getDropOffZones());
            }

            if (weightCond && fragileCond && scheduleCond && zoneCond) {
                deliveryPersonsMatched.add(deliveryPerson);
            }
        }

        if (deliveryPersonsMatched.size() > 0) {
            // choose a delivery man 
            DeliveryPerson deliveryPersonChoosen = deliveryPersonsMatched.get(0);
            for (int i = 0; i < deliveryPersonsMatched.size(); i++) {
                DeliveryPerson deliveryPerson = deliveryPersonsMatched.get(i);
                if (deliveryPerson.getType() == DeliveryPersonType.VendeurLivreur) {
                    deliveryPersonChoosen = deliveryPerson;
                    break;
                }
            }

            // create and return the assignation
            Assignation assignation = new Assignation();
            assignation.setDeliveryPerson(deliveryPersonChoosen);
            assignation.setOrder(order);
            return assignationRepository.save(assignation);
        }

        // return null if none of the delivery men are compatible
        return null;
    }

    /**
     * @param deliveryTimes times of the deliveries
     * @param deliveryPersonSchedules schedules of the delivery person
     * @return  true if delivery times are including in delivery person's schedules, false otherwise
     */
    private boolean checkSchedule(List<Interval> deliveryTimes, List<Schedule> deliveryPersonSchedules) {
        int deliveryDay = getDay(deliveryTimes.get(0).getStart());

        for (int i = 0; i < deliveryTimes.size(); i++) {
            Date deliveryTimeStart = deliveryTimes.get(i).getStart();
            Date deliveryTimeEnd = deliveryTimes.get(i).getStart();

            for (int j = 0; j < deliveryPersonSchedules.size(); j++) {
                Schedule schedule = deliveryPersonSchedules.get(j);
                int scheduleDay = schedule.getDay();
    
                Date scheduleTimeStart = schedule.getStartTime();
                boolean startCond = getHour(deliveryTimeStart) > getHour(scheduleTimeStart)
                    || (getHour(deliveryTimeStart) == getHour(scheduleTimeStart)
                        && getMinute(deliveryTimeStart) >= getMinute(scheduleTimeStart));
    
                Date scheduleTimeEnd = schedule.getEndTime();
                boolean endCond = getHour(deliveryTimeEnd) < getHour(scheduleTimeEnd)
                    || (getHour(deliveryTimeEnd) == getHour(scheduleTimeEnd)
                        && getMinute(deliveryTimeEnd) <= getMinute(scheduleTimeEnd));
                        
                if (deliveryDay == scheduleDay && startCond && endCond) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * @param collectPoints collect points (ie : shops adresses)
     * @param shopDeliveryPerson shop associated of the delivery person
     * @return true if shop adresses are equals to the shop address associated to the delivery person
     */
    private boolean checkPickUpPoint(List<CollectPoint> collectPoints, Geolocation shopDeliveryPerson) { 
        List<Double> shopCollectPoint = shopDeliveryPerson.getCoordinates();     
        for (int i = 0; i < collectPoints.size(); i++) {
            CollectPoint collectPoint = collectPoints.get(i);
            List<Double> coordinatesCollectPoint = collectPoint.getGeolocation().getCoordinates();
            
            if (Double.compare(shopCollectPoint.get(0), coordinatesCollectPoint.get(0)) != 0
                || Double.compare(shopCollectPoint.get(1), coordinatesCollectPoint.get(1)) != 0) {
                    return false;
            }

        }  
        return true;
    }

    /**
     * @param collectPoints collect points (ie : shops adresses)
     * @param pickUpsDeliveryPerson pick up zones of the delivery person
     * @return true if shop adresses are included in pick up zones of the delivery person
     */
    private boolean checkPickUpZone(List<CollectPoint> collectPoints, List<Zone> pickUpsDeliveryPerson) {      
        for (int i = 0; i < collectPoints.size(); i++) {
            CollectPoint collectPoint = collectPoints.get(i);
            List<Double> coordinatesCollectPoint = collectPoint.getGeolocation().getCoordinates();

            for (int j = 0; j < pickUpsDeliveryPerson.size(); j++) {
                Zone pickUpDeliveryPerson = pickUpsDeliveryPerson.get(j);
                List<Double> coordinatesPickUp = pickUpDeliveryPerson.getGeolocation().getCoordinates();

                double distancePickUp = distance(
                    coordinatesCollectPoint.get(0), coordinatesPickUp.get(0),
                    coordinatesCollectPoint.get(1), coordinatesPickUp.get(1));
                if (distancePickUp > pickUpDeliveryPerson.getRadius()) {
                    return true;
                }
            }

        }        
        return false;
    }

    /**
     * @param deliveryPoints delivery points
     * @param dropOffsDeliveryPerson drop off zones of the delivery person
     * @return true if delivery points are included in drop off zones of the delivery person
     */
    private boolean checkDropOffZone(List<DeliveryPoint> deliveryPoints, List<Zone> dropOffsDeliveryPerson) {
        for (int i = 0; i < deliveryPoints.size(); i++) {
            DeliveryPoint deliveryPoint = deliveryPoints.get(i);
            List<Double> coordinates = deliveryPoint.getGeolocation().getCoordinates();

            for (int j = 0; j < dropOffsDeliveryPerson.size(); j++) {
                Zone dropOffDeliveryPerson = dropOffsDeliveryPerson.get(j);
                List<Double> coordinatesPickUp = dropOffDeliveryPerson.getGeolocation().getCoordinates();

                double distanceDropOff = distance(
                    coordinates.get(0), coordinatesPickUp.get(0),
                    coordinates.get(1), coordinatesPickUp.get(1));
                if (distanceDropOff <= dropOffDeliveryPerson.getRadius()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Calculate distance between two points in latitude and longitude.
     * @param lat1 latitude start point
     * @param lat2 latitude end point
     * @param lon1 longitude start point
     * @param lon2 longitude end point
     * @return Distance in Kilometers
     */
    private double distance(double lat1, double lat2, double lon1, double lon2) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
        double theta = lon1 - lon2;
        double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return dist;
    }

    /**
     * @param date date on which we want the day of week
     * @return the day of week of date
     */
    private int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return day == 1 ? 7 : day - 1;
    }

    /**
     * @param date date on which we want the hour
     * @return the hour of date
     */
    private int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR);
    }

    /**
     * @param date date on which we want the minute
     * @return the minute of date
     */
    private int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }
}
