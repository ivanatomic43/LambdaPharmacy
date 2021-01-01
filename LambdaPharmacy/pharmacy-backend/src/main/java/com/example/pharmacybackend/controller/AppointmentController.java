package com.example.pharmacybackend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.pharmacybackend.dto.AppointmentDTO;
import com.example.pharmacybackend.security.TokenUtils;
import com.example.pharmacybackend.services.AppointmentService;

import com.example.pharmacybackend.services.UserServiceImpl;
import com.example.pharmacybackend.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/appointment")
public class AppointmentController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/createAppointment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<?> makeAnAppointment(@RequestBody AppointmentDTO newApp) {

        AppointmentDTO createdAppointment = appointmentService.createAppointment(newApp);

        if (createdAppointment == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(createdAppointment, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllPredefined/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll(@PathVariable("id") Long id) {

        List<AppointmentDTO> appointmentList = appointmentService.getAllPredefined(id);

        if (appointmentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(appointmentList, HttpStatus.OK);
    }

    @RequestMapping(value = "/reserveAppointment/{id}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?> getAll(@PathVariable("id") Long id, HttpServletRequest request) {

        String myToken = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(myToken);
        User user = userService.findByUsername(username);

        boolean reserved = appointmentService.reserveAppointment(id, user.getId());

        if (!reserved) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(reserved, HttpStatus.OK);
    }

    @RequestMapping(value = "/getPatientAppointments")
    public ResponseEntity<?> getPatientAppointments(HttpServletRequest request) {

        String myToken = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(myToken);
        User user = userService.findByUsername(username);

        List<AppointmentDTO> myAppointments = appointmentService.getAllPatientAppointments(user.getId());

        if (myAppointments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(myAppointments, HttpStatus.OK);
    }

    @RequestMapping(value = "/cancelAppointment/{id}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?> cancelAppointment(@PathVariable("id") Long id, HttpServletRequest request) {

        String myToken = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(myToken);
        User user = userService.findByUsername(username);

        boolean cancelled = appointmentService.cancelAppointment(id, user.getId());

        if (!cancelled) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
