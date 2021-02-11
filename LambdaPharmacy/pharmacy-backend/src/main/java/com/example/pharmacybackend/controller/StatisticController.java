package com.example.pharmacybackend.controller;

import java.text.ParseException;
import java.util.List;

import com.example.pharmacybackend.dto.EarningsDTO;
import com.example.pharmacybackend.dto.StatisticDTO;

import com.example.pharmacybackend.security.TokenUtils;
import com.example.pharmacybackend.services.StatisticService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/statistic")
public class StatisticController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private StatisticService statisticService;

    @RequestMapping(value = "/getAppointmentStatistic/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<?> getAppointmentStatistic(@PathVariable("id") Long id) throws ParseException {

        List<StatisticDTO> retList = statisticService.getAppointmentStatistic(id);

        return new ResponseEntity<>(retList, HttpStatus.OK);
    }

    @RequestMapping(value = "/getMedicinesStatistic/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<?> getMedicinesStatistic(@PathVariable("id") Long id) throws ParseException {

        List<StatisticDTO> retList = statisticService.getMedicinesStatistic(id);

        return new ResponseEntity<>(retList, HttpStatus.OK);
    }

    @RequestMapping(value = "/getTotalEarnings/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<?> getTotalEarnings(@PathVariable("id") Long id) throws ParseException {

        List<EarningsDTO> retList = statisticService.getTotalEarnings(id);

        return new ResponseEntity<>(retList, HttpStatus.OK);
    }

}
