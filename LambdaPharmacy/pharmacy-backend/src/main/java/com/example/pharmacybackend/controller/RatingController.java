package com.example.pharmacybackend.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import com.example.pharmacybackend.model.User;

import com.example.pharmacybackend.repository.UserRepository;
import com.example.pharmacybackend.security.TokenUtils;
import com.example.pharmacybackend.services.RatingService;
import com.example.pharmacybackend.services.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.*;

@RestController
@RequestMapping(value = "/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    TokenUtils tokenUtils;

    @RequestMapping(value = "/rateUser/{id}/{rate}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?> rateUser(@PathVariable("id") Long id, @PathVariable("rate") Integer rate,
            HttpServletRequest request) {

        String myToken = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(myToken);
        User user = userService.findByUsername(username);

        User myUser = userRepository.findOneById(id);

        if (myUser.getAuthority().getName().equals("ROLE_DERMATOLOGIST")) {

            double rating = ratingService.rateDermatologist(id, rate, user);
            return new ResponseEntity<>(rating, HttpStatus.OK);
        } else if (myUser.getAuthority().getName().equals("ROLE_PHARMACIST")) {
            double rating = ratingService.ratePharmacist(id, rate, user);
            return new ResponseEntity<>(rating, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/changeRateUser/{id}/{rate}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?> changeRateUser(@PathVariable("id") Long id, @PathVariable("rate") Integer rate,
            HttpServletRequest request) {

        String myToken = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(myToken);
        User user = userService.findByUsername(username);

        User myUser = userRepository.findOneById(id);

        if (myUser.getAuthority().getName().equals("ROLE_DERMATOLOGIST")) {

            double rating = ratingService.changeRateDermatologist(id, rate, user);
            return new ResponseEntity<>(rating, HttpStatus.OK);
        } else if (myUser.getAuthority().getName().equals("ROLE_PHARMACIST")) {
            double rating = ratingService.changeRatePharmacist(id, rate, user);
            return new ResponseEntity<>(rating, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/ratePharmacy/{id}/{rate}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?> ratePharmacy(@PathVariable("id") Long id, @PathVariable("rate") Integer rate,
            HttpServletRequest request) {

        String myToken = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(myToken);
        User user = userService.findByUsername(username);

        double rating = ratingService.ratePharmacy(id, rate, user);

        if (rating != 0) {
            return new ResponseEntity<>(rating, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/changeRatePharmacy/{id}/{rate}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?> changeRatePharmacy(@PathVariable("id") Long id, @PathVariable("rate") Integer rate,
            HttpServletRequest request) {

        String myToken = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(myToken);
        User user = userService.findByUsername(username);

        double rating = ratingService.changeRatePharmacy(id, rate, user);

        if (rating != 0) {
            return new ResponseEntity<>(rating, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/rateMedicine/{id}/{rate}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?> rateMedicine(@PathVariable("id") Long id, @PathVariable("rate") Integer rate,
            HttpServletRequest request) {

        String myToken = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(myToken);
        User user = userService.findByUsername(username);

        double rating = ratingService.rateMedicine(id, rate, user);

        if (rating != 0) {
            return new ResponseEntity<>(rating, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/changeRateMedicine/{id}/{rate}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?> changeRateMedicine(@PathVariable("id") Long id, @PathVariable("rate") Integer rate,
            HttpServletRequest request) {

        String myToken = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(myToken);
        User user = userService.findByUsername(username);

        double rating = ratingService.changeRateMedicine(id, rate, user);

        if (rating != 0) {
            return new ResponseEntity<>(rating, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
