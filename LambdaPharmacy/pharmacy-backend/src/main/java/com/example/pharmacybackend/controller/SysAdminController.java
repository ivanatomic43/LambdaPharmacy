package com.example.pharmacybackend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.pharmacybackend.dto.ComplaintDTO;
import com.example.pharmacybackend.dto.ReplyDTO;
import com.example.pharmacybackend.model.User;
import com.example.pharmacybackend.security.TokenUtils;
import com.example.pharmacybackend.services.SysAdminService;
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
@RequestMapping(value = "/sysAdmin")
public class SysAdminController {

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private SysAdminService sysAdminService;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/sendComplaint", method = RequestMethod.POST)
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?> sendComplaint(@RequestBody ComplaintDTO complaint, HttpServletRequest request) {

        String myToken = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(myToken);
        User user = userService.findByUsername(username);

        boolean created = sysAdminService.sendComplaint(complaint, user.getId());

        if (!created)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(created, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllComplaints", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('SYS_ADMIN')")
    public ResponseEntity<?> getAllComplaints(HttpServletRequest request) {

        List<ComplaintDTO> complaints = sysAdminService.getAllComplaints();

        if (complaints.isEmpty())
            return new ResponseEntity<>(complaints, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }

    @RequestMapping(value = "/sendReply", method = RequestMethod.POST)
    @PreAuthorize("hasRole('SYS_ADMIN')")
    public ResponseEntity<?> sendReply(@RequestBody ReplyDTO reply, HttpServletRequest request) {

        boolean done = sysAdminService.sendReply(reply);

        if (!done)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(done, HttpStatus.OK);
    }

}
