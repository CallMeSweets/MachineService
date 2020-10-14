import { Injectable } from '@angular/core';
import {ToastrService} from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private toastrService: ToastrService) { }

  showSuccessMsg(msg: string, title: string){
    this.toastrService.success('Some warning message', 'some title', {closeButton: true, positionClass: "toast-top-center", progressBar: true});
  }

}
