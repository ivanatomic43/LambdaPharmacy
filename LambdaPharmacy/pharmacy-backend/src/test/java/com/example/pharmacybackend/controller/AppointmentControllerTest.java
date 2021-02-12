package com.example.pharmacybackend.controller;

import com.example.pharmacybackend.TestUtil;
import com.example.pharmacybackend.constants.AppointmentConstants;
import com.example.pharmacybackend.dto.AppointmentDTO;
import com.example.pharmacybackend.enumerations.AppointmentStatus;
import com.example.pharmacybackend.enumerations.AppointmentType;
import com.example.pharmacybackend.model.Appointment;
import com.example.pharmacybackend.model.Dermatologist;
import com.example.pharmacybackend.model.EmployedDermatologist;
import com.example.pharmacybackend.model.Patient;
import com.example.pharmacybackend.model.Pharmacy;
import com.example.pharmacybackend.model.User;
import com.example.pharmacybackend.security.JwtAuthenticationRequest;

import org.h2.expression.function.ToDateParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.access.method.P;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@WebAppConfiguration
public class AppointmentControllerTest {

    private static final String URL_PREFIX = "/appointment";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    public void getAllPredefinedTest() throws Exception {

        mockMvc.perform(post(URL_PREFIX + "/getAllPredefined" + "/" + AppointmentConstants.DB_PHARM_ID))
                .andExpect(status().isOk()).andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(AppointmentConstants.DB_COUNT_APP)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(AppointmentConstants.DB_APP_ID.intValue())))
                .andExpect(jsonPath("$.[*].dermatologistID").value(hasItem(AppointmentConstants.DB_DERM_ID.intValue())))
                .andExpect(jsonPath("$.[*].duration").value(hasItem(AppointmentConstants.DB_DURATION)));
    }

}
