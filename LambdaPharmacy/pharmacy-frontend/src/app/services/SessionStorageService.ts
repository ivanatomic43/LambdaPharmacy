import { Injectable } from '@angular/core';
import {UserDTO} from '../model/UserDTO';

const TOKEN_KEY = 'AuthToken';
const USERNAME_KEY = 'AuthUsername';
const AUTHORITIES_KEY = 'AuthAuthorities';
const USER_ID = 'AuthoritiesID';
const USER_DATA = 'UserData';

@Injectable({
  providedIn: 'root'
})
export class SessionStorageService {

  constructor() { }

  signOut() {
    window.sessionStorage.clear();
  }

  public saveToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public saveUsername(username: string) {
    window.sessionStorage.removeItem(USERNAME_KEY);
    window.sessionStorage.setItem(USERNAME_KEY, username);
  }


  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public getUserName(): string {
    return sessionStorage.getItem(USERNAME_KEY);
  }


  public saveUserData(profile: UserDTO) {
    window.sessionStorage.removeItem(USER_DATA);
    const profileJson = JSON.stringify(profile);
    window.sessionStorage.setItem(USER_DATA, profileJson);
  }

  public getUserData(): UserDTO {
    const profile = sessionStorage.getItem(USER_DATA);
    return JSON.parse(profile);
  }


}
