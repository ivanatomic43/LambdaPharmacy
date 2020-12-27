import { AppointmentService } from './../../services/AppointmentService';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, Input, OnInit } from '@angular/core';
import { NewAppointmentDTO } from 'src/app/model/NewAppointmentDTO';

@Component({
  selector: 'app-make-an-app-d',
  templateUrl: './make-an-app-d.component.html',
  styleUrls: ['./make-an-app-d.component.css']
})
export class MakeAnAppDComponent implements OnInit {

  pharmacyID: number;
  dermatologistID: number;
  newApForm : FormGroup;
  newAppointment : NewAppointmentDTO;

  constructor(
    private router: Router,
    private route : ActivatedRoute,
    private appointmentService : AppointmentService
  ) { }

  ngOnInit() {

      this.pharmacyID=this.route.snapshot.params.pid;
      this.dermatologistID= this.route.snapshot.params.did;

      this.newApForm = new FormGroup({
        dateOfAppointment: new FormControl('', [Validators.required]),
        meetingTime: new FormControl('', [Validators.required]),
        duration: new FormControl('', [Validators.required]),
        price: new FormControl('', [Validators.required])
      });



  }

  makeAnAppointment(){

      this.newAppointment = new NewAppointmentDTO(
        this.pharmacyID,
        this.dermatologistID,
        this.newApForm.get('dateOfAppointment').value,
        this.newApForm.get('meetingTime').value,
        this.newApForm.get('duration').value,
        this.newApForm.get('price').value
      );

        this.appointmentService.createAppointment(this.newAppointment).subscribe(
          response => {
             alert("Appointment created!");
             this.router.navigate(['/pharmacy-details/' + this.pharmacyID]);
          }, error =>{
            alert("Error...Time invalid/Dermatologist is not available!");
          }
          );



  }






}
