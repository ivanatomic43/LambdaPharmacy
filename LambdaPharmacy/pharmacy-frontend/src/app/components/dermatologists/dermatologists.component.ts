import { DermatologistService } from './../../services/DermatologistService';
import { Router } from '@angular/router';
import { SessionStorageService } from 'src/app/services/SessionStorageService';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { UserProfileDTO } from 'src/app/model/UserProfileDTO';


@Component({
  selector: 'app-dermatologists',
  templateUrl: './dermatologists.component.html',
  styleUrls: ['./dermatologists.component.css']
})
export class DermatologistsComponent implements OnInit {

  dataSource: MatTableDataSource<UserProfileDTO>;
  dermatologists: UserProfileDTO[] = [];
  displayedColumns: string[] = [
    'id',
    'firstName',
    'lastName',
    'username',
    'email',
    'address',
    'phoneNumber',
    'role'
  ];


  constructor(
    private router: Router,
    private sessionStorageService: SessionStorageService,
    private dermatologistService : DermatologistService

  ) { }

  ngOnInit() {
    this.fetchDermatologists();
  }


 fetchDermatologists(){
  this.dermatologistService.getAllDermatologists().subscribe(response => {
    this.dermatologists= response;
    console.log("usao u fetch");
    this.dataSource= new MatTableDataSource(this.dermatologists);
});

 }

}
