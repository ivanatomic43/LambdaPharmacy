package com.example.pharmacybackend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.pharmacybackend.dto.ComplaintDTO;
import com.example.pharmacybackend.dto.ReplyDTO;
import com.example.pharmacybackend.dto.UserRequestDTO;
import com.example.pharmacybackend.dto.VacationDTO;
import com.example.pharmacybackend.model.Supplier;
import com.example.pharmacybackend.model.SystemAdministrator;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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

    @RequestMapping(value = "/getAllVacationRequests", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<?> getAllVacations(HttpServletRequest request) {

        List<VacationDTO> vacations = this.sysAdminService.getAllVacationRequests();

        if (vacations.isEmpty())
            return new ResponseEntity<>(vacations, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(vacations, HttpStatus.OK);
    }

    @RequestMapping(value = "/approveVacation/{id}/{userid}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<?> approveVacation(@PathVariable("id") Long id, @PathVariable("userid") Long userid) {

        boolean approved = this.sysAdminService.approveVacation(id, userid);

        if (!approved)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(approved, HttpStatus.OK);
    }

    @RequestMapping(value = "/denyVacation/{id}/{userid}/{text}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<?> denyVacation(@PathVariable("id") Long id, @PathVariable("userid") Long userid,
            @PathVariable("text") String text) {

        boolean denied = this.sysAdminService.denyVacation(id, userid, text);

        if (!denied)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(denied, HttpStatus.OK);
    }

    @RequestMapping(value = "/registerSysAdmin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('SYS_ADMIN')")
    public ResponseEntity<?> registerSysAdmin(@RequestBody UserRequestDTO userRequest) {

        User exist = userService.findByUsername(userRequest.getUsername());

        if (exist != null) {
            logger.warn("SYSADMINUSERNAMEEXIST");
            return new ResponseEntity<>(exist, HttpStatus.CONFLICT);
        }

        SystemAdministrator a = new SystemAdministrator();
        a = sysAdminService.registerSysAdmin(userRequest);

        if (a == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    @RequestMapping(value = "/registerSupplier", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('SYS_ADMIN')")
    public ResponseEntity<?> registerSupplier(@RequestBody UserRequestDTO userRequest) {

        User exist = userService.findByUsername(userRequest.getUsername());

        if (exist != null) {
            logger.warn("SUPPLIERUSERNAMEEXIST");
            return new ResponseEntity<>(exist, HttpStatus.CONFLICT);
        }

        Supplier s = new Supplier();
        s = sysAdminService.registerSupplier(userRequest);

        if (s == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(s, HttpStatus.OK);
    }

}
