import {AfterViewInit, Component, Inject, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {Subscription} from 'rxjs';
import {ErrorStateMatcher, MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {Product} from '../../../models/Product';

@Component({
  selector: 'app-edit-create-product',
  templateUrl: './edit-create-product.component.html',
  styleUrls: ['./edit-create-product.component.scss']
})
export class EditCreateProductComponent implements OnInit, OnDestroy, AfterViewInit {


  productMatcher = new ProductMatcher();
  actualProduct: Product;
  locationForm: FormGroup;
  subscriptions$: { [key: string]: Subscription } = {};
  isSaveButtonDisabled$ = true;

  constructor(private formBuilder: FormBuilder,
              @Inject(MAT_DIALOG_DATA) public data: DialogData,
              public dialogRef: MatDialogRef<EditCreateProductComponent>) { }

  ngOnInit() {
    this.locationForm = this.formBuilder.group({
      name: new FormControl('', [Validators.required,
                                                         Validators.maxLength(20)]),

      price: new FormControl('', [Validators.required,
                                                          Validators.pattern('^[0-9]+$'),
                                                          Validators.maxLength(4)]),

      weight: new FormControl('', [Validators.required,
                                                           Validators.pattern('^[0-9]+$'),
                                                           Validators.maxLength(4)]),

      type: new FormControl('', [Validators.required,
                                                         Validators.maxLength(255)]),
    });

    if(this.data.product) {
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
      name: this.data.product.name,
      price: this.data.product.price,
      weight: this.data.product.weight,
      type: this.data.product.productType
    });
  }

  onCancel(){
    this.dialogRef.close();
  }


  onSave(){
    this.updateActualProductsWithForm();
    this.dialogRef.close(this.actualProduct);
  }

  updateActualProductsWithForm(){
    this.actualProduct = {
      id: null,
      name: this.locationForm.get('name').value,
      price: this.locationForm.get('price').value,
      weight: this.locationForm.get('weight').value,
      productType: this.locationForm.get('type').value,
      ownerId: 1
    };
    if(this.data.product){
      this.actualProduct.id = this.data.product.id;
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
  formType: 'NEW PRODUCT' | 'EDIT PRODUCT';
  confirmButton: 'SAVE' | 'ADD';
  product: Product;
}

export class ProductMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    return !control.valid;
  }
}
