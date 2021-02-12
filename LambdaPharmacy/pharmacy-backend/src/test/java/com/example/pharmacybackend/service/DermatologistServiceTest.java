package com.example.pharmacybackend.service;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.pharmacybackend.constants.DermatologistConstants;
import com.example.pharmacybackend.model.Dermatologist;
import com.example.pharmacybackend.repository.DermatologistRepository;
import com.example.pharmacybackend.services.DermatologistService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DermatologistServiceTest {

    @Mock
    private DermatologistRepository dermatologistRepositoryMock;

    @Mock
    private Dermatologist dermatologistMock;

    @InjectMocks
    private DermatologistService dermatologistService;

    @Test
    public void testFindAll() {

        Dermatologist dermatologist = new Dermatologist();
        dermatologist.setId(DermatologistConstants.DB_ID);
        dermatologist.setFirstName(DermatologistConstants.DB_FIRST_NAME);
        dermatologist.setLastName(DermatologistConstants.DB_LAST_NAME);
        dermatologist.setUsername(DermatologistConstants.DB_USERNAME);
        dermatologist.setEmail(DermatologistConstants.DB_EMAIL);
        dermatologist.setAddress(DermatologistConstants.DB_ADDRESS);
        dermatologist.setPhoneNumber(DermatologistConstants.DB_PHONE);

        when(dermatologistRepositoryMock.findAll()).thenReturn(Arrays.asList(dermatologist));

        List<Dermatologist> dermatologists = dermatologistService.findAll();

        assertThat(dermatologists).hasSize(1);
        assertThat(dermatologists).hasSize(1);

        verify(dermatologistRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(dermatologistRepositoryMock);
    }

    @Test
    public void testFindOne() {
        // 1. Definisanje ponašanja mock objekata
        when(dermatologistRepositoryMock.findOneById(DermatologistConstants.DB_ID)).thenReturn(dermatologistMock);

        // 2. Akcija
        Dermatologist derm = dermatologistService.findOneById(DermatologistConstants.DB_ID);

        // 3. Verifikacija: asertacije i/ili verifikacija interakcije sa mock objektima
        assertEquals(dermatologistMock, derm);

        verify(dermatologistRepositoryMock, times(1)).findOneById(DermatologistConstants.DB_ID);
        verifyNoMoreInteractions(dermatologistRepositoryMock);
    }

    @Test
    @Transactional
    public void testSave() {
        when(dermatologistRepositoryMock.save(dermatologistMock)).thenReturn(dermatologistMock);

        Dermatologist savedDermatologist = dermatologistService.save(dermatologistMock);

        assertThat(savedDermatologist, is(equalTo(dermatologistMock)));
        verify(dermatologistRepositoryMock, times(1)).save(dermatologistMock);
        verifyNoMoreInteractions(dermatologistRepositoryMock);
    }

}
