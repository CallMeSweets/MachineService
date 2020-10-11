import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {SupportComponent} from './contact/support/support.component';
import {ReactiveFormsModule} from '@angular/forms';
import {
  MatButtonModule,
  MatCardModule, MatCheckboxModule, MatDialog,
  MatDividerModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule, MatPaginatorModule,
  MatSelectModule, MatSortModule, MatTableModule, MatDialogModule, MAT_DIALOG_DATA, MatDialogRef
} from '@angular/material';
import {FlexModule} from '@angular/flex-layout';
import { LocationComponent } from './configuration/location/location.component';
import { HttpClientModule} from '@angular/common/http';
import { EditCreateLocationComponent } from './configuration/location/edit-create/edit-create-location.component';
import {OnlyNumberDirective} from './directives/OnlyNumber';
import { EditCreateProductComponent } from './configuration/product/edit-create-product/edit-create-product.component';
import {ProductComponent} from './configuration/product/product.component';
import { ConfirmDialogWindowComponent } from './shared/confirm-dialog-window/confirm-dialog-window.component';



@NgModule({
  declarations: [
    SupportComponent,
    LocationComponent,
    ProductComponent,
    EditCreateLocationComponent,
    OnlyNumberDirective,
    EditCreateProductComponent,
    ConfirmDialogWindowComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatIconModule,
    FlexModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatDividerModule,
    MatTableModule,
    MatCheckboxModule,
    MatSortModule,
    MatPaginatorModule,
    MatDialogModule,
    HttpClientModule
  ],
  exports: [
    SupportComponent
  ],
  bootstrap: [SupportComponent],
  entryComponents: [EditCreateLocationComponent,
                    EditCreateProductComponent,
                    ConfirmDialogWindowComponent],
  providers: [MatDialog,
    { provide: MAT_DIALOG_DATA, useValue: {} },
    { provide: MatDialogRef, useValue: {} }]
})
export class MenuModule { }
