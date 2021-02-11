package com.example.pharmacybackend.controller;

import java.nio.charset.Charset;

import com.example.pharmacybackend.TestUtil;
import com.example.pharmacybackend.constants.MedicineConstants;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
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
public class MedicineControllerTest {

    private static final String URL_PREFIX = "/medicine";

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
    public void testGetPharmacyMedicines() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/getPharmacyMedicines" + "/" + MedicineConstants.DB_PHARMACY_ID))
                .andExpect(status().isOk()).andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(MedicineConstants.DB_COUNT_PHARMACY_MEDICINES)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(MedicineConstants.DB_PHARM_MED_MED_ID.intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(MedicineConstants.DB_PHARM_MED_NAME)))
                .andExpect(jsonPath("$.[*].quantity").value(hasItem(MedicineConstants.DB_PHARM_MED_QUANTITY)));
    }

}
