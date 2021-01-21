import { Subscription } from 'rxjs';
import { EditPrice } from './../../model/EditPrice';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { EditPricelistComponent } from 'src/app/components/edit-pricelist/edit-pricelist.component';
import { PharmacyService } from './../../services/PharmacyService';
import { PharmacyDTO } from './../../model/PharmacyDTO';
import { MedicinePreview } from 'src/app/model/MedicinePreview';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MedicineService } from 'src/app/services/medicineService';
import { MatSort, MatTableDataSource, MatDialog } from '@angular/material';
import { MatDialogConfig} from '@angular/material';

@Component({
  selector: 'app-pricelist',
  templateUrl: './pricelist.component.html',
  styleUrls: ['./pricelist.component.css']
})
export class PricelistComponent implements OnInit {

  @ViewChild(MatSort) sort: MatSort;

  dataSource3 : MatTableDataSource<MedicinePreview>;
  fetchedMedicines: MedicinePreview[] = [];
  displayedColumns3: string[] = [
       'id',
       'name',
       'quantity',
       'rating',
       'price',
        'priceLastsTo',
        'action'
  ];

  fetchedPharmacy: PharmacyDTO;
  pharmacyID: number;
  clicked = false;

  editForm: FormGroup;
  editID:number;
  editName: string;
  editPrice: number;
  editLastsTo: any;
  editPriceModel : EditPrice;

  constructor(
    private medicineService : MedicineService,
    private pharmacyService : PharmacyService,
    private dialog : MatDialog,
    private formBuilder : FormBuilder

  ) { }

  ngOnInit() {

    this.editForm = this.formBuilder.group({
      editID : [{value: '', disabled: true}, Validators.required],
      editName : [{value: '', disabled: true}, Validators.required],
      editPrice : ['', Validators.required],
      editLastsTo : ['', Validators.required]
    });


    this.pharmacyService.getAdminsPharmacy().subscribe(response => {
      this.fetchedPharmacy = response;
      this.pharmacyID = this.fetchedPharmacy.id;


      this.fetchMedicines(this.pharmacyID);

    });


  }

  fetchMedicines(pharmacyID:number){
    this.medicineService.getPharmacyMedicines(this.pharmacyID).subscribe(response => {
      this.fetchedMedicines= response;
      this.dataSource3 = new MatTableDataSource(this.fetchedMedicines);
      this.dataSource3.sort = this.sort;
    });
  }

  editPriceClick(id:number, name: string, price: number, priceLastsTo:any){

  this.clicked = true;
    this.editID= id;
    this.editName = name;
    this.editPrice =price;

    const date = new Date(priceLastsTo);
    this.editLastsTo = date;



  }

  cancel(){
    this.clicked = false;
  }


  save(){

    this.editPriceModel = new EditPrice(
      this.editForm.get('editID').value,
      this.editForm.get('editName').value,
      this.editForm.get('editPrice').value,
      this.editForm.get('editLastsTo').value
    );

    this.medicineService.editPrice(this.editPriceModel, this.pharmacyID).subscribe(response=> {

      alert("Price changed!");
      this.fetchMedicines(this.pharmacyID);
      this.clicked= false;
    });

  }

/*
  alert(priceFrom);
  alert(priceTo);
    this.medicineService.populateForm(name, price, priceFrom, priceTo);
    const dialogConfig = new MatDialogConfig();
      dialogConfig.disableClose = true;
      dialogConfig.autoFocus=true;
      dialogConfig.width= "30%";

      this.dialog.open(EditPricelistComponent,dialogConfig);
  }
*/
}
