package com.example.pharmacybackend.controller;

import com.example.pharmacybackend.dto.MedicineDTO;
import com.example.pharmacybackend.dto.OfferDTO;
import com.example.pharmacybackend.dto.PurchaseOrderDTO;
import com.example.pharmacybackend.model.User;
import com.example.pharmacybackend.security.TokenUtils;
import com.example.pharmacybackend.services.OrderService;
import com.example.pharmacybackend.services.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/getAllOrders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PHARMACY_ADMIN') or hasRole('SUPPLIER')")
    public ResponseEntity<?> getAllOrders(HttpServletRequest request) {

        String myToken = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(myToken);
        User user = userService.findByUsername(username);

        if (user.getAuthority().getName().equals("ROLE_PHARMACY_ADMIN")) {

            List<PurchaseOrderDTO> retList = orderService.getAllOrders(user.getId());
            return new ResponseEntity<>(retList, HttpStatus.OK);

        } else if (user.getAuthority().getName().equals("ROLE_SUPPLIER")) {

            List<PurchaseOrderDTO> retList = orderService.getAllOrdersSupplier();
            return new ResponseEntity<>(retList, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/giveOffer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('SUPPLIER')")
    public ResponseEntity<?> giveOffer(@RequestBody OfferDTO offer, HttpServletRequest request) {

        String myToken = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(myToken);
        User user = userService.findByUsername(username);

        boolean done = orderService.giveOffer(offer, user.getId());

        if (!done) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(done, HttpStatus.OK);
    }

    @RequestMapping(value = "/getOrderDetails/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<?> getOrderDetails(HttpServletRequest request, @PathVariable("id") Long orderID) {

        PurchaseOrderDTO dto = orderService.getOrderDetails(orderID);

        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "/getOrdersMedicines/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<?> getOrdersMedicines(HttpServletRequest request, @PathVariable("id") Long orderID) {

        List<MedicineDTO> retList = orderService.getOrdersMedicines(orderID);

        if (retList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(retList, HttpStatus.OK);
    }

    @RequestMapping(value = "/getOffers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<?> getOffers(HttpServletRequest request, @PathVariable("id") Long orderID) {

        List<OfferDTO> retList = orderService.getOffers(orderID);

        if (retList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(retList, HttpStatus.OK);
    }

    @RequestMapping(value = "/acceptOffer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<?> acceptOffer(HttpServletRequest request, @PathVariable("id") Long offerID) {

        String myToken = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(myToken);
        User user = userService.findByUsername(username);

        boolean accepted = orderService.acceptOffer(offerID, user.getId());

        if (!accepted) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
