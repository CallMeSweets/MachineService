import { Injectable } from '@angular/core';
import {User} from "../model/User";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }


  performLogin(user: User) {
    console.log(user.email + " " + user.password);
  }


}
