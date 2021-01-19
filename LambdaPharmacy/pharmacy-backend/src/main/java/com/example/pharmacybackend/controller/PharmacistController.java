package com.example.pharmacybackend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.pharmacybackend.dto.PharmacistDTO;
import com.example.pharmacybackend.dto.SearchPharmacistParams;
import com.example.pharmacybackend.dto.UserDTO;
import com.example.pharmacybackend.model.Authority;
import com.example.pharmacybackend.model.User;
import com.example.pharmacybackend.security.TokenUtils;
import com.example.pharmacybackend.services.PharmacistService;
import com.example.pharmacybackend.services.UserServiceImpl;

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
@RequestMapping(value = "/pharmacist")
public class PharmacistController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PharmacistService pharmacistService;

    @RequestMapping(value = "/getAllPharmacists", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PHARMACY_ADMIN') or hasRole('SYS_ADMIN')")
    public ResponseEntity<?> getAllPharmacists() {

        List<UserDTO> list = userService.getAllPharmacists();

        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @RequestMapping(value = "/addPharmacist/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<?> addPharmacist(@PathVariable("id") Long id, @RequestBody PharmacistDTO dto) {

        User exist = userService.findByUsername(dto.getUsername());

        if (exist != null) {
            logger.warn("PHARMACISTSUSERNAMEEXIST");
        }

        PharmacistDTO addedPharmacist = pharmacistService.registerPharmacist(dto, id);

        if (addedPharmacist == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(addedPharmacist, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllPharmacistForPharmacy/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllPharm(@PathVariable("id") Long id) {

        List<PharmacistDTO> retPharm = pharmacistService.getAllPharmForPharmacy(id);

        if (retPharm.isEmpty()) {
            return new ResponseEntity<>(retPharm, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(retPharm, HttpStatus.OK);

    }

    @RequestMapping(value = "/seePharmacists/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAvailablePharmacists(@PathVariable("id") Long id,
            @RequestBody SearchPharmacistParams sp) {

        List<PharmacistDTO> retPharm = pharmacistService.getAvailablePharmacists(id, sp);

        if (retPharm.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(retPharm, HttpStatus.OK);

    }

    @RequestMapping(value = "/removePharmacist/{id}/{did}")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<?> removePharmacist(@PathVariable("id") Long id, @PathVariable("did") Long did) {

        boolean removed = pharmacistService.removePharmacist(id, did);

        if (!removed) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllP", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PATIENT') or hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<?> getAllP(HttpServletRequest request) {

        String myToken = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(myToken);
        User user = userService.findByUsername(username);

        List<PharmacistDTO> retPharm = new ArrayList<>();

        if (user.getAuthority().getName().equals("ROLE_PATIENT")) {
            retPharm = pharmacistService.getAllP();
        }

        if (user.getAuthority().getName().equals("ROLE_PHARMACY_ADMIN")) {
            retPharm = pharmacistService.getAdmins(user.getId());
        }

        if (retPharm.isEmpty()) {
            return new ResponseEntity<>(retPharm, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(retPharm, HttpStatus.OK);

    }

}
