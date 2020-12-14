
import { SessionStorageService } from 'src/app/services/SessionStorageService';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import {SortPipe} from "../../services/SortPipe";
import {MatTableDataSource} from '@angular/material/table';
import { AppointmentDDTO } from 'src/app/model/AppointmentDDTO';
import { AppointmentService} from "../../services/AppointmentService";

@Component({
  selector: 'app-history-of-app-d',
  templateUrl: './history-of-app-d.component.html',
  styleUrls: ['./history-of-app-d.component.css']
})
export class HistoryOfAppDComponent implements OnInit {

  dataSource: MatTableDataSource<AppointmentDDTO>;
  appointments: AppointmentDDTO[] = [];
  displayedColumns: string[] = [
    'id',
    'type',
    'dateOfAppointment',
    'duration',
    'dermatologistName',
    'dermatologistSurname'
  ];



  constructor(
    private router : Router,
    private route: ActivatedRoute,
    private appointmentService: AppointmentService,
    private sessionStorageService: SessionStorageService,
    private sortPipe : SortPipe
  ) { }

  ngOnInit() {
    this.fetchAppointments();
  }

  fetchAppointments(){
      this.appointmentService.getAllAppointments().subscribe(response => {
          this.appointments= response;
          this.dataSource= new MatTableDataSource(this.appointments);
      });
  }

  sortChange(value) {
    console.log(value);
    if (value == 'dateA')
      this.appointments = this.sortPipe.transform(
        this.appointments,
        'asc',
        'dateOfAppointment'
      );

    if (value == 'dateD')
      this.appointments = this.sortPipe.transform(
        this.appointments,
        'desc',
        'dateOfAppointment'
      );

    if (value == 'priceA')
      this.appointments = this.sortPipe.transform(
        this.appointments,
        'asc',
        'price'
      );

    if (value == 'priceD')
      this.appointments = this.sortPipe.transform(
        this.appointments,
        'desc',
        'price'
      );

      if (value == 'durationA')
      this.appointments = this.sortPipe.transform(
        this.appointments,
        'asc',
        'duration'
      );
      if (value == 'durationD')
      this.appointments = this.sortPipe.transform(
        this.appointments,
        'desc',
        'duration'
      );
  }

}
