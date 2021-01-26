import { RatingService } from './../../services/RatingService';
import { NumberValueAccessor } from '@angular/forms/src/directives';
import { FormGroup, FormBuilder, Validators} from '@angular/forms';
import { ComplaintComponent } from './../complaint/complaint.component';
import { UserService } from './../../services/UserService';
import { PharmacyDTO } from './../../model/PharmacyDTO';
import { AppointmentPreview } from 'src/app/model/AppointmentPreview';

import { SessionStorageService } from 'src/app/services/SessionStorageService';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import {SortPipe} from "../../services/SortPipe";
import {MatTableDataSource} from '@angular/material/table';
import { AppointmentDDTO } from 'src/app/model/AppointmentDDTO';
import { AppointmentService} from "../../services/AppointmentService";
import { DermDTO } from 'src/app/model/DermDTO';
import { MatDialog, MatDialogConfig } from '@angular/material';

@Component({
  selector: 'app-history-of-app-d',
  templateUrl: './history-of-app-d.component.html',
  styleUrls: ['./history-of-app-d.component.css']
})
export class HistoryOfAppDComponent implements OnInit {

  dataSource: MatTableDataSource<AppointmentPreview>;
  appointments: AppointmentPreview[] = [];
  displayedColumns: string[] = [
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

  dataSourceD: MatTableDataSource<DermDTO>;
  doctors: DermDTO[]= [];
  displayedColumnsD: string[]= [
    'id',
    'name',
    'role',
    'rating',
    'myRate',
    'action'
  ];

  dataSourcePh : MatTableDataSource<PharmacyDTO>;
  pharmacies: PharmacyDTO[] = [];
  displayedColumnsPh : string[] = [
    'id',
    'name',
    'address',
    'rating',
    'action'
  ];

  complaintForm : FormGroup;

  //rating
  clickedUser = false;
  rateForm: FormGroup;
  rateUserID : number;
  rate: number;

  clickedPharmacy = false;
  ratePharmacyForm : FormGroup;
  ratePharmacyID: number;
  ratePharm:number;
  clickedChange= false;
  clickedRate = false;



  constructor(
    private router : Router,
    private route: ActivatedRoute,
    private appointmentService: AppointmentService,
    private sessionStorageService: SessionStorageService,
    private sortPipe : SortPipe,
    private userService: UserService,
    private formBuilder : FormBuilder,
    private ratingService : RatingService
  ) { }

  ngOnInit() {
    this.fetchEndedAppointments();
    this.fetchDoctors();
    this.fetchPharmacies();

    this.rateForm = this.formBuilder.group({
      rateUserID : [{value: '', disabled:true}, Validators.required],
      rate : ['', Validators.required]
    });

    this.ratePharmacyForm = this.formBuilder.group({
      ratePharmacyID : [{value: '', disabled: true}, Validators.required],
      ratePharm : ['', Validators.required]
    });

  }

  fetchEndedAppointments(){
      this.appointmentService.getEndedAppointments().subscribe(response => {
          this.appointments= response;
          this.dataSource= new MatTableDataSource(this.appointments);
      });
  }

  fetchDoctors(){
    this.appointmentService.getVisitedDoctors().subscribe(response => {
      this.doctors = response;
      this.dataSourceD = new MatTableDataSource(this.doctors);
    });


  }

  fetchPharmacies(){
    this.appointmentService.getVisitedPharmacies().subscribe(response => {
      this.pharmacies = response;
      this.dataSourcePh = new MatTableDataSource(this.pharmacies);
    });
  }

  writeComplaint(id:number){

    this.router.navigate(['/complaint/' + id]);

  }

  rateUserClick(id:number){
    this.clickedUser = true;
    this.clickedChange = false;
    this.rateUserID = id;
  }


  ratePharmacyClick(id:number){
    this.clickedPharmacy = true;
    this.ratePharmacyID = id;
  }

  cancelUserRate(){
    this.clickedUser = false;

  }

  cancelPharmacyRate(){
    this.clickedPharmacy = false;

  }

  rateUser(id:number, rate:number){
      this.rateUserID = this.rateForm.get('rateUserID').value;
      this.rate = this.rateForm.get('rate').value;
      this.clickedUser = false;
      this.ratingService.rateUser(this.rateUserID, this.rate).subscribe(response => {

        alert("User rated!");

        this.fetchDoctors();
      });
  }

  ratePharmacy(id:number, rate:number){
      this.ratePharmacyID = this.ratePharmacyForm.get('ratePharmacyID').value;
      this.ratePharm = this.ratePharmacyForm.get('ratePharm').value;

      this.ratingService.ratePharmacy(this.ratePharmacyID, this.ratePharm).subscribe(response =>{
        alert("Pharmacy rated!");
        this.fetchPharmacies();
      });
  }

  changeUserRateClick(id:number){
    this.clickedUser = true;
    this.clickedChange = true;
    this.rateUserID = id;

  }

  changeUserRate(id:number, rate:number){

    this.rateUserID = this.rateForm.get('rateUserID').value;
    this.rate = this.rateForm.get('rate').value;

    this.ratingService.changeRateUser(this.rateUserID, this.rate).subscribe(response => {

      alert("User rate changed!");
      this.clickedUser = false;

      this.fetchDoctors();
    });
  }



}
