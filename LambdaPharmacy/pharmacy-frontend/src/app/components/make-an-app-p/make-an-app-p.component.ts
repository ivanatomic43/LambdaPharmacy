import { AppointmentService } from 'src/app/services/AppointmentService';
import { NewCounceling } from './../../model/NewCounceling';
import { NewAppointmentDTO } from './../../model/NewAppointmentDTO';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { PharmacyPharm } from './../../model/PharmacyPharm';
import { PharmacistService } from 'src/app/services/PharmacistService';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SearchService } from 'src/app/services/SearchService';
import { MatTableDataSource } from '@angular/material';
import { SearchPharmacistParams } from 'src/app/model/SearchPharmacistParams';
import { DermDTO } from 'src/app/model/DermDTO';

@Component({
  selector: 'app-make-an-app-p',
  templateUrl: './make-an-app-p.component.html',
  styleUrls: ['./make-an-app-p.component.css']
})
export class MakeAnAppPComponent implements OnInit {

  dataSource2 : MatTableDataSource<PharmacyPharm>;
   pharmacies : PharmacyPharm[] = [];
  displayedColumns2: string[]= [
    'id',
    'name',
    'address',
    'rating',
    'price',
    'action'
  ];

  dataSource3 : MatTableDataSource<DermDTO>;
   pharmacists : DermDTO[] = [];
  displayedColumns3: string[]= [
    'id',
    'name',
    'surname',
    'rating',
    'workFrom',
    'workTo',
    'price',
    'action'
  ];

  searchPharmacistForm : FormGroup;
  searchParams : SearchPharmacistParams;
  show = false;
  newAppointment: NewCounceling;
  pharmacyID : number;

  constructor(
    private router: Router,
    private pharmacistService: PharmacistService,
    private searchService : SearchService,
    private formBuilder : FormBuilder,
    private appointmentService :AppointmentService

  ) { }

  ngOnInit() {

    this.searchPharmacistForm = new FormGroup({
      searchPharmacistDate: new FormControl('', [Validators.required]),
      searchPharmacistTime: new FormControl('', [Validators.required])
    });

  }

  searchPharmacist(){

    this.searchParams = new SearchPharmacistParams(
      this.searchPharmacistForm.get('searchPharmacistDate').value,
      this.searchPharmacistForm.get('searchPharmacistTime').value
    );

     this.searchService.searchPharmacist(this.searchParams).subscribe( response => {
       this.pharmacies = response;
       this.dataSource2 = new MatTableDataSource(this.pharmacies);

     }, error => {
       alert("No search results!");
     });

  }

  seePharm(id:number){
    this.show = true;
    this.pharmacyID= id;
    this.searchParams = new SearchPharmacistParams(
      this.searchPharmacistForm.get('searchPharmacistDate').value,
      this.searchPharmacistForm.get('searchPharmacistTime').value
    );

    this.pharmacistService.seePharmacists(id, this.searchParams).subscribe(response => {
      this.pharmacists = response;
      this.dataSource3 = new MatTableDataSource(this.pharmacists);
    }, error => {
      alert("There is no available pharmacist for chosen params...");
    })
  }

  reserveCounceling(id:number){

    this.newAppointment = new NewCounceling(
      this.pharmacyID,
      id,
      this.searchPharmacistForm.get('searchPharmacistDate').value,
      this.searchPharmacistForm.get('searchPharmacistTime').value,
      0
    );

    this.appointmentService.reserveCounceling(this.newAppointment).subscribe(response => {
      alert("Appointment reserved! Check your e-mail...");
      this.router.navigate(['/home']);

    }, error =>{
     alert("Date and time are not available");
    })




  }

}
