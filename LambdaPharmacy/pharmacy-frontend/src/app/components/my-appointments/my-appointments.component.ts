import { Subscription } from 'rxjs';
import { SessionStorageService } from 'src/app/services/SessionStorageService';
import { UserProfileDTO } from './../../model/UserProfileDTO';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { Router } from '@angular/router';
import { AppointmentPreview } from 'src/app/model/AppointmentPreview';
import { AppointmentService } from 'src/app/services/AppointmentService';
import { AuthService } from 'src/app/services/AuthService';
import { UserDTO } from 'src/app/model/UserDTO';
import { UserService } from 'src/app/services/UserService';

@Component({
  selector: 'app-my-appointments',
  templateUrl: './my-appointments.component.html',
  styleUrls: ['./my-appointments.component.css']
})
export class MyAppointmentsComponent implements OnInit {

  dataSource2 : MatTableDataSource<AppointmentPreview>;
  appointments: AppointmentPreview[] = [];
  displayedColumns2: string[]= [
    'id',
    'dateOfAppointment',
    'meetingTime',
    'doctor',
    'role',
    'type',
    'duration',
    'price',
    'action'
  ];

  usernameForm : string;
  loggedUser: UserDTO;

  constructor
    (
    private authService: AuthService,
    private userService : UserService,
    private appointmentService: AppointmentService,
    private router: Router,
    private sessionStorageService : SessionStorageService) {}

  ngOnInit() {

    this.usernameForm = this.sessionStorageService.getUserName();
      this.userService.getMyUser(this.usernameForm).subscribe(response => {
        this.loggedUser = response;


      });


      this.fetchAppointments();
  }

  fetchAppointments(){
    this.appointmentService.getPatientAppointments().subscribe(
      response => {
        this.appointments= response;
        this.dataSource2 = new MatTableDataSource(this.appointments);
      }, error => {
        if(error.status == 404){
          this.dataSource2 = new MatTableDataSource();
          console.log("There is no appointments for this patient...");
        }
      }
    );
  }


  cancel(id:number){
    this.appointmentService.cancelAppointment(id).subscribe(response => {
      alert("Appointment is cancelled!");
      this.fetchAppointments();
    }, error => {
      if(error.status == 400){
        alert("You can't cancel appointment!");
      }
    });


  }

   end(id:number){
    this.appointmentService.endAppointment(id).subscribe(response => {
      alert("Ended..The appointment is now in your history of appointments...");
      this.fetchAppointments();
    });


   }
}
