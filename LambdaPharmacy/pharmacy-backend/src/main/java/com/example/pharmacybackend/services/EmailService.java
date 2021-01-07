package com.example.pharmacybackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.pharmacybackend.model.Patient;
import com.example.pharmacybackend.model.User;
import com.example.pharmacybackend.repository.PatientRepository;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private PatientRepository patientRepository;

	@Async
	public void sendActivationLink(Patient user) throws MailException {

		SimpleMailMessage mail = new SimpleMailMessage();
		String link = "http://localhost:8051/auth/registrationConfirm/" + user.getEmail();
		mail.setTo(user.getEmail());

		mail.setFrom("no.reply.lambdapharmacy@gmail.com");
		mail.setSubject("Activation link");
		mail.setText("To finish registration, click link: " + link);
		javaMailSender.send(mail);
	}

	@Async
	public void sendDenyMail(User user) throws MailException {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom("no.reply.medclinic@gmail.com");
		mail.setSubject("Rejection mail");
		mail.setText("Sorry to inform you, clinic centre administrator denied your request for registration");
		javaMailSender.send(mail);

	}

	@Async
	public void sendAppointmentReservationMail(User user) throws MailException {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom("no.reply.medclinic@gmail.com");
		mail.setSubject("Appointment reservation");
		mail.setText(
				"Dear patient, your appointment with dermatologist has been made. Thank you for your trust. Best regards, LambdaPharmacy team.");
		javaMailSender.send(mail);
	}

	@Async
	public void sendMedicineReservationMail(Patient patient, Long reservationID) throws MailException {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(patient.getEmail());
		mail.setFrom("no.reply.medclinic@gmail.com");
		mail.setSubject("Medicine reservation");
		mail.setText(
				"Dear patient, the medicine you have chosen has been successfully reserved. The number of your reservation is: "
						+ reservationID + ". Thank you for your trust. Best regards, LambdaPharmacy team.");
		javaMailSender.send(mail);
	}
}