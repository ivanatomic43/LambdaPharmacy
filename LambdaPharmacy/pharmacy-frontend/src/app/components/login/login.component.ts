import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from '../../services/AuthService';
import {FormBuilder, FormGroup} from '@angular/forms';
import {FormControl, Validators} from '@angular/forms';
import {LoginParams} from '../../model/LoginParams';
import {BehaviorSubject, Subject} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../../services/UserService';
import {UserDTO} from '../../model/UserDTO';
import {DisplayMessage} from '../../shared/models/display-message';
import {takeUntil} from 'rxjs/operators';
import {SessionStorageService} from '../../services/SessionStorageService';
import {ShareService} from '../../services/ShareService';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {
  title = 'Login';
  githubLink = 'https://github.com/bfwg/angular-spring-starter';
  notification: DisplayMessage;
  errormessage: string;
  returnUrl: string;
  private ngUnsubscribe: Subject<void> = new Subject<void>();


  // login
  private profil: UserDTO;
  isLogged = false;
  submitted = false;
  success = false;
  private loginParams: LoginParams;
  loginForm: FormGroup;
  handleAuth = new BehaviorSubject<string>(null);
  handleRole = new BehaviorSubject<string>(null);


  constructor(
    private authService: AuthService,
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private sessionStorageService: SessionStorageService,
    private shareService: ShareService


  ) { }

  ngOnInit() {
    this.route.params
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((params: DisplayMessage) => {
        this.notification = params;
      });
    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams.returnUrl || '/';
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(64)])],
      password: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(32)])]
    });
  }

  ngOnDestroy() {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  repository() {
    window.location.href = this.githubLink;
  }




  login() {
    this.notification = undefined;
    this.submitted = true;

    this.loginParams = new LoginParams(
      this.loginForm.get('username').value,
      this.loginForm.get('password').value
    );

    this.authService.login(this.loginParams).subscribe(response => {
        console.log('USao u suss');
        this.profil = response;
        this.sessionStorageService.saveToken(response.token);
        this.sessionStorageService.saveUsername(response.username);
        this.sessionStorageService.saveUserData(this.profil);

        this.isLogged = true;
        this.shareService.sendProfile(this.profil);
        this.shareService.sendIsLogged(this.isLogged);
        console.log(this.profil);


        this.router.navigate(['/patient']);
      },
      error => {
        this.submitted = false;
        this.notification = {msgType: 'error', msgBody: 'Incorrect username or password.'};
      }

    );


  }


}
