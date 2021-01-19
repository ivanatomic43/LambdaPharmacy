import { SearchService } from 'src/app/services/SearchService';
import { SearchComponent } from './../search/search.component';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { DermDTO } from 'src/app/model/DermDTO';
import { DermatologistService } from './../../services/DermatologistService';
import { Router } from '@angular/router';
import { SessionStorageService } from 'src/app/services/SessionStorageService';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { UserProfileDTO } from 'src/app/model/UserProfileDTO';
import { UserDTO } from 'src/app/model/UserDTO';
import { AuthService } from 'src/app/services/AuthService';


@Component({
  selector: 'app-dermatologists',
  templateUrl: './dermatologists.component.html',
  styleUrls: ['./dermatologists.component.css']
})
export class DermatologistsComponent implements OnInit {

  //sys admin
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

  loaded = false;
  isSysAdmin = false;
  profil: UserDTO;

  //patient & pharmacy admin
  isPatient = false;
  isPharmacyAdmin = false;

  dataSource3: MatTableDataSource<DermDTO>;
  dermatologistsList: DermDTO[] = [];
  displayedColumns3: string[] = [
    'id',
    'firstName',
    'lastName',
    'rating',
    'from',
    'to',
    'price',
    'pharmacyName',
    'action'
  ];

  searchForm: FormGroup;
  searchName : string;
  searchSurname: string;

  constructor(
    private router: Router,
    private sessionStorageService: SessionStorageService,
    private dermatologistService : DermatologistService,
    private authService : AuthService,
    private formBuilder: FormBuilder,
    private searchService : SearchService

  ) { }

  ngOnInit() {


    this.searchForm = this.formBuilder.group({
      searchName : ['', Validators.required],
      searchSurname : ['', Validators.required]
    });

    this.authService.getLogged().subscribe(response => {
      this.profil = response;
      const role = this.profil.authorities[0];

      if(role == 'ROLE_PATIENT') {
        this.isPatient = true;
        this.fetchDermatologists();



      } else {
        this.isPatient = false;
      }
      if(role == 'ROLE_PHARMACY_ADMIN'){
        this.isPharmacyAdmin= true;
        this.fetchDermatologists();

      } else {
        this.isPharmacyAdmin = false;
      }
      if(role == 'ROLE_SYS_ADMIN'){
        this.isSysAdmin = true;
        this.fetchRegistredDermatologists();
      } else {
        this.isSysAdmin = false;
      }
    });

  }


 fetchRegistredDermatologists(){
  this.dermatologistService.getAllDermatologists().subscribe(response => {
    this.dermatologists= response;

    this.dataSource= new MatTableDataSource(this.dermatologists);
});

 }

 registerDermForm(){
  this.router.navigate(['/register-dermatologist']);
}

fetchDermatologists(){
    this.dermatologistService.getDermatologists().subscribe(response => {
      this.dermatologistsList = response;
      this.dataSource3 = new MatTableDataSource(this.dermatologistsList);
    });
}

 search(){

  this.searchName = this.searchForm.get('searchName').value;
  this.searchSurname = this.searchForm.get('searchSurname').value;

  this.searchService.searchDermByParams(this.searchName, this.searchSurname).subscribe(response => {
    this.dermatologistService.refreshDermatologists.next(response);
  });

 }

}
