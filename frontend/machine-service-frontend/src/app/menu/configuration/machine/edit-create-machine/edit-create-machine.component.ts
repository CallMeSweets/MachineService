import {AfterViewInit, Component, Inject, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {Subscription} from 'rxjs';
import {ErrorStateMatcher, MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {Machine} from '../../../models/Machine';
import {Location} from '../../../models/Location';

@Component({
  selector: 'app-edit-create-machine',
  templateUrl: './edit-create-machine.component.html',
  styleUrls: ['./edit-create-machine.component.scss']
})
export class EditCreateMachineComponent implements OnInit, OnDestroy, AfterViewInit {

  machineMatcher = new MachineMatcher();
  actualMachine: Machine;
  machineForm: FormGroup;
  subscriptions$: { [key: string]: Subscription } = {};
  isSaveButtonDisabled$ = true;
  actualLocation: Location;


  constructor(private formBuilder: FormBuilder,
              @Inject(MAT_DIALOG_DATA) public data: DialogData,
              public dialogRef: MatDialogRef<EditCreateMachineComponent>) { }

  ngOnInit() {
    this.machineForm = this.formBuilder.group({
      name: new FormControl('', [Validators.required,
        Validators.maxLength(20)]),

      totalSpace: new FormControl('', [Validators.required,
        Validators.pattern('^[0-9]+$'),
        Validators.maxLength(4)]),

      type: new FormControl('', [Validators.required,
        Validators.maxLength(25)]),

      description: new FormControl('', [Validators.required,
        Validators.maxLength(255)]),

      location: new FormControl(null, [Validators.required]),
    });

    if (this.data.machine) {
      this.initForm();
    }

    this.isSaveButtonDisabled$ = !this.machineForm.valid;
    this.subscriptions$.isSaveButtonDisabled = this.machineForm.valueChanges.subscribe(value => {{
      this.isSaveButtonDisabled$ = !this.machineForm.valid;
    }});
  }

  ngAfterViewInit(): void {
    // console.log(this.data.userLocations);
  }

  initForm() {
    this.machineForm.patchValue({
      name: this.data.machine.name,
      totalSpace: this.data.machine.totalSpace,
      type: this.data.machine.machineType,
      description: this.data.machine.description,
      location: this.data.machine.locationDTO
    });

    this.actualLocation = this.data.machine.locationDTO;
  }

  onCancel() {
    this.dialogRef.close();
  }


  onSave() {
    this.updateActualProductsWithForm();
    this.dialogRef.close(this.actualMachine);
  }

  updateActualProductsWithForm() {
    this.actualMachine = {
      id: null,
      name: this.machineForm.get('name').value,
      totalSpace: this.machineForm.get('totalSpace').value,
      machineType: this.machineForm.get('type').value,
      description: this.machineForm.get('description').value,
      ownerId: 1,
      locationDTO: this.actualLocation
    };
    if (this.data.machine) {
      this.actualMachine.id = this.data.machine.id;
    }
  }

  hasError(controlName: string, errorName: string) {
    if (!this.machineForm || !this.machineForm.controls[controlName]) {
      return false;
    }
    return this.machineForm.controls[controlName].hasError(errorName);
  }

  ngOnDestroy(): void {
    Object.values(this.subscriptions$).forEach((subscription) => subscription.unsubscribe());
  }

  setActualLocation(location: Location) {
    this.actualLocation = location;
    this.machineForm.get('location').setValue(location);
  }
}


export interface DialogData {
  formType: 'NEW MACHINE' | 'EDIT MACHINE';
  confirmButton: 'SAVE' | 'ADD';
  machine: Machine;
  userLocations: Location[];
}

export class MachineMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    return !control.valid;
  }
}
