import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {DialogData} from '../../configuration/location/edit-create/edit-create-location.component';
import {Location} from '../../models/Location';

@Component({
  selector: 'app-confirm-dialog-window',
  templateUrl: './confirm-dialog-window.component.html',
  styleUrls: ['./confirm-dialog-window.component.scss']
})
export class ConfirmDialogWindowComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: DialogData,
              public dialogRef: MatDialogRef<ConfirmDialogWindowComponent>) { }

  ngOnInit() {
  }

  onDismiss() {
    this.dialogRef.close(false);
  }

  onConfirm() {
    this.dialogRef.close(true);
  }

}

export interface DialogData {
  message: string;
}
