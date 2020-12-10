import { SessionStorageService } from 'src/app/services/SessionStorageService';
import { PasswordDTO } from './../model/PasswordDTO';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {LoginParams} from '../model/loginParams';
import {BehaviorSubject, Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {RegistrationParams} from '../model/registrationParams';
import {ApiService} from './ApiService';
import {ConfigService} from './ConfigService';
import {UserService} from './UserService';
import {UserDTO} from '../model/UserDTO';
import {map} from 'rxjs/operators';



const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Accept': 'application/json'

  })

};

const authUrl = 'http://localhost:8051/auth';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = authUrl + '/login';
  private getLoggedUrl = authUrl + '/getLoggedUser';
  private logoutUrl = authUrl + '/logout';
  private registerPatientUrl = authUrl + '/registerPatient';
  private changePasswordUrl = authUrl + '/changePassword';

  constructor(
    private http: HttpClient,
    private router: Router,
    private apiService: ApiService,
    private config: ConfigService,
    private userService: UserService,
    private sessionStorageService: SessionStorageService

  ) {}

  private access_token = null;
  myToken : string;

  handleAuth = new BehaviorSubject<string>(null);
  handleRole = new BehaviorSubject<string>(null);
  private tokenExpirationTimer: any;

  signup(user) {
    const signupHeaders = new HttpHeaders({
      Accept: 'application/json',
      'Content-Type': 'application/json'
    });
    return this.apiService.post(this.config.signup_url, JSON.stringify(user), signupHeaders)
      .pipe(map(() => {
        console.log('Sign up success');
      }));
  }

  login(loginParams: LoginParams): Observable<UserDTO> {

    // const body = `username=${user.username}&password=${user.password}`;

    return this.http.post<UserDTO>(this.loginUrl, loginParams, httpOptions);
  }



  registerPatient(registrationParams: RegistrationParams) {
    return this.http.post<any>(this.registerPatientUrl, registrationParams, httpOptions);

  }
  getLogged(): Observable <UserDTO> {
    this.myToken = this.sessionStorageService.getToken();
    //const headers = new HttpHeaders({"TokenAuthBic": this.myToken})
    console.log('usao u get logged u auth service');
    return this.http.get<UserDTO>(this.getLoggedUrl, { headers: new HttpHeaders({'TokenAuthBic': this.myToken})});
  }

  autoLogin() {
    const token = JSON.parse(localStorage.getItem(this.access_token));
    if (!token) {
      return;
    }

    if (token) {
      this.handleAuth.next(token.accessToken);
      this.handleRole.next(token.role);
    }
  }


  logout(): Observable < any > {
    this.handleAuth.next(null);
    this.handleRole.next(null);
    this.router.navigate(['/home']);
    localStorage.clear();
    if (this.tokenExpirationTimer) {
      clearTimeout(this.tokenExpirationTimer);
    }
    this.tokenExpirationTimer = null;



    return this.http.get<any>(this.logoutUrl, httpOptions);

  }


  tokenIsPresent() {
    return this.access_token != undefined && this.access_token != null;
  }

  getToken() {
    return this.access_token;
  }

  changePassword(data: PasswordDTO){

      alert("USAO OVDE");
    return this.http.put<any>(this.changePasswordUrl, data);



  }
}
