import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatTableDataSource } from '@angular/material';
import { SysAdminService } from './../../services/SysAdminService';
import { Component, OnInit } from '@angular/core';
import { VacationPreview } from 'src/app/model/VacationPreview';

@Component({
  selector: 'app-vacations',
  templateUrl: './vacations.component.html',
  styleUrls: ['./vacations.component.css']
})
export class VacationsComponent implements OnInit {

  show = false;
  dataSource2 : MatTableDataSource<VacationPreview>;
  fetchedVacations : VacationPreview [] = [];
  displayedColumns2 : string[] = [
    'id',
    'userid',
    'name',
    'surname',
    'role',
    'vacationFrom',
    'vacationTo',
    'status',
    'action'

  ];

  vacationForm : FormGroup;
  denyid: number;
  denyUserID: number;
  denyText : string;

  constructor(
    private sysAdminService:SysAdminService,
    private formBuilder : FormBuilder

    ) { }

  ngOnInit() {

    this.vacationForm = this.formBuilder.group({
      denyid : [{value : '', disabled: true}, Validators.required],
      denyUserID: [{value: '', disabled: true}, Validators.required],
      denyText : ['', Validators.required]
    });
    this.fetchVacations();

  }

  fetchVacations(){
    this.sysAdminService.getAllVacationRequests().subscribe(response => {
      this.fetchedVacations = response;
      this.dataSource2= new MatTableDataSource(this.fetchedVacations);
    });
  }

  approve(id:number, userid:number, status:string){

    if(status == 'APPROVED'){
      alert("This request has already been approved!");
      return;
    }
    if(status == 'DENIED'){
      alert("This request has been denied!");
      return;
    }
    if(status == 'WAITING_FOR_APPROVAL'){
      this.sysAdminService.approveRequest(id, userid).subscribe(response => {
        alert("Vacation approved!");
        this.fetchVacations();
      });
    }

  }

  deny(id:number, userid:number, status:string){
    if(status == 'APPROVED'){
      alert("This request has been approved!");
      return;
    }
    if(status=='DENIED'){
      alert("This request has already been denied!");
      return;
    }
    if(status == 'WAITING_FOR_APPROVAL'){
     this.show = true;
     this.denyid = id;
     this.denyUserID = userid;

    }

  }

  denyVacation(){

    this.denyText= this.vacationForm.get('denyText').value;


    this.sysAdminService.denyRequest(this.denyid, this.denyUserID, this.denyText).subscribe(response => {
      alert("Vacation denied!");
      this.fetchVacations();
      this.show= false;
    });

  }

  cancel(){
    this.show= false;
  }
}
