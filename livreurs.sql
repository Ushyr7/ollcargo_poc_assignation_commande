CREATE TABLE IF NOT EXISTS DeliveryPersons (
   id int generated always as identity,
   userId int not null,
   type char(50) not null,
   transportation char(50) not null,
   maxWeight real not null,
   canDeliverFragile bool not null,
   mail char(50) not null,
   primary key(id)
);

CREATE TABLE IF NOT EXISTS Zones (
	id int generated always as identity,
	name char(100) not null,
	latitude float(53) not null,
	longitude float(53) not null, 
	primary key(id)
);

CREATE TABLE IF NOT EXISTS PickUpZones (
	id int generated always as identity,
	deliveryPersonId int not null,
	zoneId int not null,
	primary key(id),
	constraint fk_deliverPerson foreign key(deliveryPersonId)
		references deliveryPersons(id) on delete cascade,
	constraint fk_zone foreign key(zoneId)
		references Zones(id) on delete cascade
);

CREATE TABLE IF NOT EXISTS DropOffZones (
	id int generated always as identity,
	deliveryPersonId int not null,
	zoneId int not null,
	primary key(id),
	constraint fk_deliverPerson foreign key(deliveryPersonId)
		references deliveryPersons(id) on delete cascade,
	constraint fk_zone foreign key(zoneId)
		references Zones(id) on delete cascade
);

CREATE TABLE IF NOT EXISTS Schedule (
	id int generated always as identity,
	deliveryPersonId int not null,
	day smallint not null check (day > 0 and day < 8),
	startTime time not null,
	endTime time not null,
	primary key(id),
	constraint fk_deliverPerson foreign key(deliveryPersonId)
		references deliveryPersons(id) on delete cascade
);

insert into Zones(name, latitude, longitude) values 
('Gare de Rouen', 49.4488, 1.09409), 
('Theatre des arts Rouen', 49.439976, 1.09006620000002),
('Saint sever Rouen', 43.759137, -0.574221);

insert into deliverypersons(userid, type, transportation, maxweight, candeliverfragile, mail) values
(1, 'standard', 'voiture', 45, true, 'livreur1@mail.com'),
(2, 'standard', 'vélo', 19.5, false, 'livreur2@mail.com'),
(3, 'standard', 'à pied', 10, true, 'livreur3@mail.com'),
(4, 'standard', 'voiture', 25, true, 'livreur4@mail.com'),
(5, 'standard', 'vélo', 10, false, 'livreur5@mail.com'),
(6, 'standard', 'à pied', 12.5, true, 'livreur6@mail.com'),
(7, 'standard', 'voiture', 25, true, 'livreur7@mail.com'),
(8, 'standard', 'vélo', 25, true, 'livreur8@mail.com'),
(9, 'standard', 'à pied', 25, true, 'livreur9@mail.com'),
(10, 'standard', 'voiture', 25, true, 'livreur10@mail.com');

insert into schedule (deliverypersonid, day, starttime, endtime) values
(1, 1, '00:00:00', '23:00:00'),
(1, 2, '00:00:00', '23:00:00'),
(1, 3, '00:00:00', '23:00:00'),
(1, 4, '00:00:00', '23:00:00'),
(1, 5, '00:00:00', '23:00:00'),
(1, 6, '00:00:00', '23:00:00'),
(1, 7, '00:00:00', '23:00:00'),
(2, 1, '00:00:00', '23:00:00'),
(2, 2, '00:00:00', '23:00:00'),
(2, 3, '00:00:00', '23:00:00'),
(2, 4, '00:00:00', '23:00:00'),
(2, 5, '00:00:00', '23:00:00'),
(2, 6, '00:00:00', '23:00:00'),
(2, 7, '00:00:00', '23:00:00'),
(3, 1, '00:00:00', '23:00:00'),
(3, 2, '00:00:00', '23:00:00'),
(3, 3, '00:00:00', '23:00:00'),
(4, 4, '00:00:00', '23:00:00'),
(5, 5, '00:00:00', '23:00:00'),
(6, 6, '00:00:00', '23:00:00'),
(7, 7, '00:00:00', '23:00:00');


insert into pickupzones(deliverypersonid, zoneid) values
(1,1),
(1,2),
(1,3),
(2,1),
(3,2),
(4,3),
(5,1),
(6,2),
(7,3),
(8,1),
(9,2),
(10,3);

insert into dropoffzones(deliverypersonid, zoneid) values
(1,1),
(1,2),
(1,3),
(2,1),
(3,2),
(4,3),
(5,1),
(6,2),
(7,3),
(8,1),
(9,2),
(10,3);

select * from deliveryPersons;