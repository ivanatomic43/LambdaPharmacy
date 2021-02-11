package com.example.pharmacybackend.controller;

import java.nio.charset.Charset;

import com.example.pharmacybackend.constants.PharmacyConstants;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@WebAppConfiguration
public class PharmacyControllerTest {

    private static final String URL_PREFIX = "/pharmacy";

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
    public void testGetPharmacies() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/getAllPharmacies")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(PharmacyConstants.DB_COUNT_PHARMACIES)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(PharmacyConstants.DB_ID.intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(PharmacyConstants.DB_NAME)))
                .andExpect(jsonPath("$.[*].description").value(hasItem(PharmacyConstants.DB_DESCRIPTION)))
                .andExpect(jsonPath("$.[*].rating").value(hasItem(PharmacyConstants.DB_RATING)));
    }

}
