import { MatTableDataSource } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { PharmacyService } from 'src/app/services/PharmacyService';
import { AuthService } from 'src/app/services/AuthService';
import { PharmacyDTO } from 'src/app/model/PharmacyDTO';
import { DermDTO } from 'src/app/model/DermDTO';

@Component({
  selector: 'app-statistic',
  templateUrl: './statistic.component.html',
  styleUrls: ['./statistic.component.css']
})
export class StatisticComponent implements OnInit {

  pharmacyID: number;
  fetchedPharmacy: PharmacyDTO;

  dataSource: MatTableDataSource<DermDTO>;
  fetchedStaff : DermDTO[] = [];
  displayedColumns : string[] = [
    'id',
    'employee',
    'role',
    'rating'
  ];


  constructor(
    private pharmacyService : PharmacyService,
    private router : Router,
    private route : ActivatedRoute,
    private authService : AuthService
  ) { }

  ngOnInit() {

    this.pharmacyID = this.route.snapshot.params.id;

    this.pharmacyService.getPharmacyById(this.pharmacyID).subscribe(response =>{
        this.fetchedPharmacy= response;
    });

    this.pharmacyService.getEmployedStaff(this.pharmacyID).subscribe(response => {
      this.fetchedStaff = response ;
      this.dataSource = new MatTableDataSource(this.fetchedStaff);
    });

  }

}
