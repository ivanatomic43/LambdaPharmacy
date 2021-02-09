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



}
