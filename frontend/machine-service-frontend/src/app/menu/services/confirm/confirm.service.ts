import { Injectable } from '@angular/core';
import {ConfirmDialogWindowComponent} from '../../shared/confirm-dialog-window/confirm-dialog-window.component';
import {MatDialog} from '@angular/material';

@Injectable({
  providedIn: 'root'
})
export class ConfirmService {

  constructor(private dialog: MatDialog) { }

  showConfirmWindow(message: string) {
    const dialogRef = this.dialog.open(ConfirmDialogWindowComponent, {
      data: {
        message
      },
      disableClose: true
    });

    return dialogRef.afterClosed();
  }

}
