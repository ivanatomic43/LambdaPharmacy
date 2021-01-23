
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


  }
