import {AfterViewInit, Component, EventEmitter, Inject, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {Observable, Subscription} from 'rxjs';
import {Location} from '../../../models/Location';
import {ErrorStateMatcher, MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';


@Component({
  selector: 'app-edit-create',
  templateUrl: './edit-create.component.html',
  styleUrls: ['./edit-create.component.scss']
})
export class EditCreateComponent implements OnInit, AfterViewInit, OnDestroy {

  @Output()
  locationEmitter = new EventEmitter<Location>();

  locationMatcher = new LocationMatcher();
  actualLocation: Location;
  locationForm: FormGroup;
  subscriptions$: { [key: string]: Subscription } = {};
  isSaveButtonDisabled$ = true;

  constructor(private formBuilder: FormBuilder,
              @Inject(MAT_DIALOG_DATA) public data: DialogData,
              public dialogRef: MatDialogRef<EditCreateComponent>) { }

  ngOnInit() {
    this.locationForm = this.formBuilder.group({
      city: new FormControl('', [Validators.required,
                                                         Validators.maxLength(20)]),

      street: new FormControl('', [Validators.required,
                                                          Validators.maxLength(20)]),

      flatNumber: new FormControl('', [Validators.required,
                                                              Validators.pattern('^[0-9]+$'),
                                                              Validators.maxLength(4)]),

      description: new FormControl('', [Validators.required,
                                                                Validators.maxLength(255)]),
    });

    if(this.data.location) {
      this.initForm();
    }

    this.isSaveButtonDisabled$ = !this.locationForm.valid;
    this.subscriptions$['isSaveButtonDisabled'] = this.locationForm.valueChanges.subscribe(value => {{
      this.isSaveButtonDisabled$ = !this.locationForm.valid;
    }});
  }

  ngAfterViewInit(): void {

  }

  initForm() {
      this.locationForm.patchValue({
        city: this.data.location.city,
        street: this.data.location.street,
        flatNumber: this.data.location.streetNumber,
        description: this.data.location.description
      });
  }

  onCancel(){
     this.dialogRef.close();
  }


  onSave(){
     this.updateActualLocationWithForm();
     this.dialogRef.close(this.actualLocation);
  }

  updateActualLocationWithForm(){
    this.actualLocation = {
      id: null,
      street: this.locationForm.get('street').value,
      city: this.locationForm.get('city').value,
      streetNumber: this.locationForm.get('flatNumber').value,
      description: this.locationForm.get('description').value,
      ownerId: 1
    }
    if(this.data.location){
      this.actualLocation.id = this.data.location.id;
    }
  }

  hasError(controlName: string, errorName: string) {
    if (!this.locationForm || !this.locationForm.controls[controlName]) {
      return false;
    }
    return this.locationForm.controls[controlName].hasError(errorName);
  }

  ngOnDestroy(): void {
    Object.values(this.subscriptions$).forEach((subscription) => subscription.unsubscribe());
  }

}


export interface DialogData {
  formType: 'NEW LOCATION' | 'EDIT LOCATION';
  location: Location;
}

export class LocationMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    return !control.valid;
  }
}
