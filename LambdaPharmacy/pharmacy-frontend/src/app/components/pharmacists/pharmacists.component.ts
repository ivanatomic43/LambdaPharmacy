import { SortPipe } from './../../services/SortPipe';
import { SearchUserDTO } from './../../model/SearchUserDTO';
import { SearchService } from 'src/app/services/SearchService';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { DermDTO } from './../../model/DermDTO';
import { MatSort, MatTableDataSource } from '@angular/material';
import { UserDTO } from 'src/app/model/UserDTO';
import { PharmacistService } from 'src/app/services/PharmacistService';
import { Component, OnInit, Input, ViewChild, AfterViewInit } from '@angular/core';
import { AuthService } from 'src/app/services/AuthService';

@Component({
  selector: 'app-pharmacists',
  templateUrl: './pharmacists.component.html',
  styleUrls: ['./pharmacists.component.css']
})
export class PharmacistsComponent implements OnInit{

  profil : UserDTO;
  isPatient = false;
  isPharmacyAdmin= false;
  searchName: string;
  searchSurname: string;
  searchForm: FormGroup;
  searchUser : SearchUserDTO;


  dataSource3 : MatTableDataSource<DermDTO>;
  fetchedPharmacists: DermDTO[] = [];
  displayedColumns3: string[] = [
       'id',
       'name',
       'surname',
       'rating',
       'workFrom',
        'workTo',
        'price',
        'pharmacy',
        'action'
  ];


  constructor(
    private authService : AuthService,
    private pharmacistService : PharmacistService,
    private formBuilder : FormBuilder,
    private searchService : SearchService,
    private sortPipe : SortPipe
  ) { }

  @ViewChild(MatSort) sort: MatSort;

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
          this.fetchAllPharmacists();



        } else {
          this.isPatient = false;
        }
        if(role == 'ROLE_PHARMACY_ADMIN'){
          this.isPharmacyAdmin= true;
          this.fetchAllPharmacists();





        } else {
          this.isPharmacyAdmin = false;
        }

      });


  }


  fetchAllPharmacists(){
    this.pharmacistService.getAllP().subscribe(response => {
      this.fetchedPharmacists = response;
      this.dataSource3 = new MatTableDataSource(this.fetchedPharmacists);
      this.dataSource3.sort = this.sort;

    });
  }

  search(){

    this.searchUser = new SearchUserDTO(this.searchForm.get('searchName').value, this.searchForm.get('searchSurname').value);


    this.searchName = this.searchForm.get('searchName').value;
    this.searchSurname = this.searchForm.get('searchSurname').value;


    this.searchService.searchPharmByParams(this.searchUser).subscribe(response => {
        //this.pharmacistService.refreshPharmacists.next(response);
        this.fetchedPharmacists = response;
            this.dataSource3 = new MatTableDataSource(this.fetchedPharmacists);
    }, error => {
     alert("No search results!");
    });
  }

  cancelSearch(){

    this.fetchAllPharmacists();
  }

  sortChange(value) {
    console.log(value);
    if (value == 'priceA')
      this.fetchedPharmacists = this.sortPipe.transform(
        this.fetchedPharmacists,
        'asc',
        'price'
      );

    if (value == 'priceD')
      this.fetchedPharmacists = this.sortPipe.transform(
        this.fetchedPharmacists,
        'desc',
        'price'
      );

    if (value == 'ratingA')
      this.fetchedPharmacists = this.sortPipe.transform(
        this.fetchedPharmacists,
        'asc',
        'rating'
      );

    if (value == 'ratingD')
      this.fetchedPharmacists = this.sortPipe.transform(
        this.fetchedPharmacists,
        'desc',
        'rating'
      );
  }
}
