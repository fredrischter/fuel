package com.acme.fuel.rest;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acme.fuel.model.Consumption;
import com.acme.fuel.service.ConsumptionService;

@RestController
@RequestMapping(path = "/consumption")
public class ConsumptionRest {

    @Autowired
    private ConsumptionService service;

    @GetMapping
    @RequestMapping(path = "/{id}")
    public Consumption get(@PathVariable Long id) {
        return service.retrieve(id);
    }

    @PostMapping
    @RequestMapping
    public void list(@RequestBody Consumption consumption) {
        service.create(consumption);
    }

    @PostMapping
    @RequestMapping(path = "/list")
    public Iterable<Consumption> list() {
        return service.list();
    }

    @GetMapping
    @RequestMapping(path = "/month")
    public int byMonth(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate month) {
        return service.byMonth(month);
    }

}
