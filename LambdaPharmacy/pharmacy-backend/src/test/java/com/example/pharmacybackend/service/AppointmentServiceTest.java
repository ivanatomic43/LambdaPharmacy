package com.example.pharmacybackend.service;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.pharmacybackend.constants.AppointmentConstants;
import com.example.pharmacybackend.constants.DermatologistConstants;
import com.example.pharmacybackend.model.Appointment;
import com.example.pharmacybackend.model.Dermatologist;
import com.example.pharmacybackend.model.EmployedDermatologist;
import com.example.pharmacybackend.model.Patient;
import com.example.pharmacybackend.model.Pharmacy;
import com.example.pharmacybackend.repository.AppointmentRepository;
import com.example.pharmacybackend.repository.DermatologistRepository;
import com.example.pharmacybackend.repository.EmployedDermatologistRepository;
import com.example.pharmacybackend.repository.PatientRepository;
import com.example.pharmacybackend.repository.PharmacyRepository;
import com.example.pharmacybackend.services.AppointmentService;
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
public class AppointmentServiceTest {

    @Mock
    private PatientRepository patientRepositoryMock;

    @Mock
    private AppointmentRepository appointmentRepositoryMock;

    @Mock
    private EmployedDermatologistRepository employedDermatologistRepositoryMock;

    @Mock
    private PharmacyRepository pharmacyRepositoryMock;

    @Mock
    private Patient patient;

    @Mock
    private Appointment appointment;

    @Mock
    private EmployedDermatologist employedDermatologistMock;

    @Mock
    private Pharmacy pharmacy;

    @InjectMocks
    private AppointmentService appointmentService;

    @Test
    public void getPatientAppointmentsTest() {

        LocalTime meeting_time = LocalTime.of(AppointmentConstants.MEETING_TIME_HOUR,
                AppointmentConstants.MEETING_TIME_MIN, AppointmentConstants.MEETIN_TIME_SEC);

    }

}
