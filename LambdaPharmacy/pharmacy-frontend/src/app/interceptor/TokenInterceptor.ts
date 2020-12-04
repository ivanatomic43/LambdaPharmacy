import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HTTP_INTERCEPTORS, HttpHeaders
} from '@angular/common/http';
import { AuthService } from '../services/AuthService';
import {SessionStorageService} from '../services/SessionStorageService';
import {environment} from '../../environments/environment';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(private sessionStorageService: SessionStorageService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler) {

    let authReq = request;
    const token = this.sessionStorageService.getToken();
    let myHeaders = new HttpHeaders();

    const parametar = 'Bearer ' + token;
    console.log('interceptr ' + parametar);

    if (token != null) {
      myHeaders = myHeaders.set('TokenAuthBic', `Bearer ${token}`);
    }

    authReq = request.clone({headers: myHeaders});

    return next.handle(authReq);
  }
}

export const httpInterceptorProviders = [
  {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  }
];
