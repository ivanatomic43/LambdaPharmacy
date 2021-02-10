import { AppointmentLoyalty } from './../model/AppointmentLoyalty';
import { Category } from 'src/app/model/Category';

import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

import { Subject, throwError } from 'rxjs';
import { Injectable } from '@angular/core';
import { stringify } from 'querystring';
import { NumberValueAccessor } from '@angular/forms/src/directives';
import { catchError, map } from 'rxjs/operators';
import { ComplaintDTO } from '../model/ComplaintDTO';
import { Reply } from '../model/Reply';

const sysAdminUrl = 'http://localhost:8051/sysAdmin';

@Injectable({providedIn: 'root'})
export class SysAdminService {


sendComplaintUrl = sysAdminUrl + '/sendComplaint';
getAllComplaintsUrl = sysAdminUrl + '/getAllComplaints';
sendReplyUrl = sysAdminUrl + '/sendReply';
getAllVacationRequestsUrl = sysAdminUrl + '/getAllVacationRequests';
approveRequestUrl = sysAdminUrl + '/approveVacation/';
denyRequestUrl = sysAdminUrl + '/denyVacation/';
editCategoryUrl = sysAdminUrl + '/editCategory';
getCategoriesUrl = sysAdminUrl + '/getCategories';
getAppLoyUrl = sysAdminUrl + '/getAppLoyalty';
editAppLoyUrl = sysAdminUrl + '/editAppLoy';

  constructor(

    private http: HttpClient,
    private router: Router,

  ){}

  sendComplaint(complaint : ComplaintDTO){
    return this.http.post(this.sendComplaintUrl, complaint,  {

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

  getAllComplaints(){
    return this.http.post(this.getAllComplaintsUrl,  {

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

  sendReply(reply: Reply){
    return this.http.post(this.sendReplyUrl, reply,  {

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


  getAllVacationRequests(){
    return this.http.post(this.getAllVacationRequestsUrl,  {

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

  approveRequest(id:number, userid:number){
    return this.http.put(this.approveRequestUrl+id +'/'+userid,  {

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

  denyRequest(id:number, userid:number, text:string){
    return this.http.put(this.denyRequestUrl+id +'/'+userid+ '/'+ text,  {

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

  editCategory(category: Category){
      return this.http.post<any>(this.editCategoryUrl, category);
  }

  getCategories(){
    return this.http.get<any>(this.getCategoriesUrl);
  }

  editAppLoy(app: AppointmentLoyalty){
    return this.http.post<any>(this.editAppLoyUrl, app);
  }

  getAppLoy(){
    return this.http.get<any>(this.getAppLoyUrl);
  }


  }
