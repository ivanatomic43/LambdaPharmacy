package com.example.pharmacybackend.service;

import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.example.pharmacybackend.constants.UserConstants;
import com.example.pharmacybackend.model.User;
import com.example.pharmacybackend.repository.UserRepository;
import com.example.pharmacybackend.services.UserServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private User userMock;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testFindAll() {

        User user = new User();
        user.setId(UserConstants.DB_ID);
        user.setFirstName(UserConstants.DB_FIRST_NAME);
        user.setLastName(UserConstants.DB_LAST_NAME);
        user.setUsername(UserConstants.DB_USERNAME);
        user.setEmail(UserConstants.DB_EMAIL);
        user.setAddress(UserConstants.DB_ADDRESS);
        user.setPhoneNumber(UserConstants.DB_PHONE);

        when(userRepositoryMock.findAll()).thenReturn(Arrays.asList(user));

        List<User> users = userService.findAll();

        assertThat(users).hasSize(1);

        verify(userRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(userRepositoryMock);
    }

    @Test
    public void testFindByUsername() {

        when(userRepositoryMock.findByUsername(UserConstants.DB_USERNAME)).thenReturn(userMock);

        User dbUser = userService.findByUsername(UserConstants.DB_USERNAME);

        assertEquals(userMock, dbUser);

        verify(userRepositoryMock, times(1)).findByUsername(UserConstants.DB_USERNAME);
        verifyNoMoreInteractions(userRepositoryMock);
    }

    @Test(expected = DataIntegrityViolationException.class)
    @Transactional
    @Rollback(true)
    public void testAddNonUniqueUsername() {
        User user = new User();
        user.setFirstName(UserConstants.NEW_FIRST_NAME);
        user.setLastName(UserConstants.NEW_LAST_NAME);
        user.setUsername(UserConstants.NEW_USERNAME); // Unique constraint violation
        user.setEmail(UserConstants.NEW_EMAIL);
        user.setAddress(UserConstants.NEW_ADDRESS);
        user.setPhoneNumber(UserConstants.NEW_PHONE);

        when(userRepositoryMock.save(user)).thenThrow(DataIntegrityViolationException.class);

        userService.saveUser(user);

        verify(userRepositoryMock, times(1)).save(user);
        verifyNoMoreInteractions(userRepositoryMock);
    }
}
