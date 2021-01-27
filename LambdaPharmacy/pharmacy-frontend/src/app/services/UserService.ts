import { ComplaintDTO } from './../model/ComplaintDTO';
import { AdministratorParams } from 'src/app/model/AdministratorParams';
import { HttpClient } from '@angular/common/http';

import {Injectable} from '@angular/core';
import {ApiService} from './ApiService';
import {ConfigService} from './ConfigService';
import {map} from 'rxjs/operators';
import {catchError} from 'rxjs/operators';
import {Observable, throwError} from "rxjs";
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { RegistrationParams } from '../model/registrationParams';

const authUrl = 'http://localhost:8051/auth';
const userUrl = 'http://localhost:8051/api';
const pharmacyUrl = 'http://localhost:8051/pharmacy';
const sysAdminUrl = 'http://localhost:8051/sysAdmin';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  currentUser;
  getMyUserUrl = authUrl + '/getMyUser';
  updateProfileUrl = userUrl + '/updateProfile';
  registerPharmacyAdministratorUrl = pharmacyUrl + '/registerPharmacyAdministrator';
  getAdminsForPharmacyUrl = pharmacyUrl + '/getAdminsForPharmacy/';
  checkIfSubUrl = userUrl + '/checkIfSub/';
  sendComplaintUrl = userUrl + '/sendComplaint';
  registerSysAdminUrl= sysAdminUrl + '/registerSysAdmin';
  registerSupplierUrl = sysAdminUrl + '/registerSupplier';


  complaintForm: FormGroup = new FormGroup({
     id: new FormControl({value: '', disabled:true}, Validators.required),
    text: new FormControl('', Validators.required)
  });

  complaintModel : ComplaintDTO;

  constructor(
    private apiService: ApiService,
    private config: ConfigService,
    private http: HttpClient
  ) {
  }

 /* populateForm(id:number){
    this.complaintModel = new ComplaintDTO(id);
    this.complaintForm.setValue(this.complaintModel);
    }
*/
  initUser() {
    const promise = this.apiService.get(this.config.refresh_token_url).toPromise()
      .then(res => {
        if (res.access_token !== null) {
          return this.getMyInfo().toPromise()
            .then(user => {
              this.currentUser = user;
            });
        }
      })
      .catch(() => null);
    return promise;
  }

  setupUser(user) {
    this.currentUser = user;
  }

  getMyInfo() {
    return this.apiService.get(this.config.whoami_url)
      .pipe(map(user => {
        this.currentUser = user;

        return user;
      }));
  }

  getAll() {
    return this.apiService.get(this.config.users_url);
  }

  getMyUser(email: string) {
    return this.http.post(this.getMyUserUrl, {
      username: email
    })
      .pipe(
        map((response: any) => {
          // tslint:disable-next-line:no-unused-expression
          const data = response
          return data;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      );
  }

  updateProfile(id: number, firstName: string, lastName: string, username: string, password: string, email:string, address: string,  phoneNumber: string) {
    return this.http.post( this.updateProfileUrl, {
      id,
      firstName,
      lastName,
      username,
      password,
      email,
      address,
      phoneNumber

    })
      .pipe(
        map((response: any) => {
          const data = response;
          return data;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      );
  }



  registerPharmacyAdministrator(adminParams : AdministratorParams){

    return this.http.post(this.registerPharmacyAdministratorUrl, adminParams, {

    })
      .pipe(
        map((response: any) => {
          // tslint:disable-next-line:no-unused-expression
          const data = response;
          console.log(data);
          return data;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      );

  }

  getAdminsForPharmacy(id: number){

    return this.http.post(this.getAdminsForPharmacyUrl + id, {

    })
      .pipe(
        map((response: any) => {
          // tslint:disable-next-line:no-unused-expression
          const data = response;
          console.log(data);
          return data;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      );

  }

  checkIfSub(id:number){

    return this.http.post(this.checkIfSubUrl + id, {

    })
      .pipe(
        map((response: any) => {
          // tslint:disable-next-line:no-unused-expression
          const data = response;
          console.log(data);
          return data;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      );

  }

  registerSysAdmin(adminParams : RegistrationParams){

    return this.http.post(this.registerSysAdminUrl, adminParams, {

    })
      .pipe(
        map((response: any) => {
          // tslint:disable-next-line:no-unused-expression
          const data = response;
          console.log(data);
          return data;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      );

  }

  registerSupplier(supplierParams : RegistrationParams){

    return this.http.post(this.registerSupplierUrl, supplierParams, {

    })
      .pipe(
        map((response: any) => {
          // tslint:disable-next-line:no-unused-expression
          const data = response;
          console.log(data);
          return data;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      );

  }



  }



