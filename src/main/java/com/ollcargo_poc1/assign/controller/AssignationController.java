package com.ollcargo_poc1.assign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ollcargo_poc1.assign.service.AutomaticAssignation;

@RestController
public class AssignationController {
    @Autowired
    private AutomaticAssignation automaticAssignation;

    @GetMapping("/auto")
    public String auto() {
        automaticAssignation.automaticPlan();
        return "yes";
    }
}
