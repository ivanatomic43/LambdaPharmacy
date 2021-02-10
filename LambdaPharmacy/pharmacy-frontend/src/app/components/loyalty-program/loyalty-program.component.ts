import { AppointmentLoyalty } from './../../model/AppointmentLoyalty';
import { NumberValueAccessor } from '@angular/forms/src/directives';
import { SysAdminService } from './../../services/SysAdminService';
import { MatTableDataSource } from '@angular/material';
import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/model/Category';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-loyalty-program',
  templateUrl: './loyalty-program.component.html',
  styleUrls: ['./loyalty-program.component.css']
})
export class LoyaltyProgramComponent implements OnInit {

  dataSource: MatTableDataSource<Category>;
  fetchedCategories : Category[] = [];
  displayedColumns: string [] = [
    'id',
    'type',
    'discount',
    'pointLimit',
    'action'

  ];

  clicked : boolean = false;
  editID: number;
  editName : string;
  editDiscount : number;
  editPointsBorder : number;
  editForm: FormGroup;
  editCategoryModel : Category;


  dataSource1 : MatTableDataSource<AppointmentLoyalty>;
  fetchedApp : AppointmentLoyalty[] = [];
  displayedColumns1 : string [] = [
    'id',
    'type',
    'points',
    'action'
  ];

  clicked1 : boolean = false;
  editID1 : number;
  editName1: string;
  editPoints : number;
  editForm1 : FormGroup;
  editAppModel :AppointmentLoyalty;

  constructor(
    private sysAdminService : SysAdminService,
    private formBuilder : FormBuilder
  ) { }

  ngOnInit() {

    this.editForm = this.formBuilder.group({
      editID : [{value: '', disabled: true}, Validators.required],
      editName : [{value: '', disabled: true}, Validators.required],
      editDiscount : ['', Validators.required],
      editPointsBorder : ['', Validators.required]
    });
    this.fetchCategories();

    this.editForm1 = this.formBuilder.group({
      editID1 : [{value: '', disabled: true}, Validators.required],
      editName1 : [{value: '', disabled: true}, Validators.required],
      editPoints : ['', Validators.required],

    });

    this.fetchApp();


  }

  fetchCategories(){

    this.sysAdminService.getCategories().subscribe(response => {
      this.fetchedCategories= response;
      this.dataSource = new MatTableDataSource(this.fetchedCategories);
    });

  }

  editClick(id:number, name: string, discount: number, pointsBorder: number){
    this.clicked= true;
    this.editID = id;
    this.editName = name;
    this.editDiscount = discount;
    this.editPointsBorder = pointsBorder



  }

  cancel(){
    this.clicked = false;
  }

  save(){

    this.editCategoryModel = new Category(
      this.editForm.get('editID').value,
      this.editForm.get('editName').value,
      this.editForm.get('editDiscount').value,
      this.editForm.get('editPointsBorder').value
    );

    this.sysAdminService.editCategory(this.editCategoryModel).subscribe(response =>{
        alert("Category updated!");
        this.fetchCategories();
        this.clicked= false;
    });

  }

   fetchApp(){

    this.sysAdminService.getAppLoy().subscribe(response => {
      this.fetchedApp= response;
      this.dataSource1 = new MatTableDataSource(this.fetchedApp);
    });


   }

   editClick1(id:number, name: string, points:number){
    this.clicked1= true;
    this.editID1 = id;
    this.editName1 = name;
    this.editPoints = points;
  }

  cancel1(){
    this.clicked1 = false;
  }

  save1(){

    this.editAppModel= new AppointmentLoyalty(
      this.editForm1.get('editID1').value,
      this.editForm1.get('editName1').value,
      this.editForm1.get('editPoints').value,

    );

    this.sysAdminService.editAppLoy(this.editAppModel).subscribe(response =>{
        alert("Points for appointment updated!");
        this.fetchApp();
        this.clicked1= false;
    });

  }


}
