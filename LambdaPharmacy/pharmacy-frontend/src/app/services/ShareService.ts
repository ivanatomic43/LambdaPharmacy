import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { UserDTO } from '../model/UserDTO';
import {UserProfileDTO} from '../model/UserProfileDTO';


@Injectable({
  providedIn: 'root'
})
export class ShareService {

  shareIsLogged = new Subject<any>();
  shareProfile = new Subject<UserDTO>();
  shareProfile1 = new Subject<UserProfileDTO>();


  constructor() { }

  sendIsLogged(isLogged: boolean) {
    this.shareIsLogged.next(isLogged);
  }

  sendProfile(profile: UserDTO) {
    this.shareProfile.next(profile);
  }

  sendProfilePreview(profile: UserProfileDTO){
    this.shareProfile1.next(profile);
  }
}
