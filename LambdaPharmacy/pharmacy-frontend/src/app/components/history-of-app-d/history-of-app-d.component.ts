import { FormGroup } from '@angular/forms';
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

  constructor(
    private router : Router,
    private route: ActivatedRoute,
    private appointmentService: AppointmentService,
    private sessionStorageService: SessionStorageService,
    private sortPipe : SortPipe,
    private userService: UserService,

  ) { }

  ngOnInit() {
    this.fetchEndedAppointments();
    this.fetchDoctors();
    this.fetchPharmacies();

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

}
