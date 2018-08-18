package com.acme.fuel.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.fuel.model.Consumption;
import com.acme.fuel.service.ConsumptionService;

@RestController
@RequestMapping(path = "/consumption")
public class ConsumptionRest {

    @Autowired
    private ConsumptionService service;

    @GetMapping
    public Iterable<Consumption> list() {
        return service.findAll();
    }

}
