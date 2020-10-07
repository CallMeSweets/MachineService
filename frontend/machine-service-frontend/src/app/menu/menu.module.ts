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
import {HttpClient, HttpClientModule} from '@angular/common/http';
import { EditCreateComponent } from './configuration/location/edit-create/edit-create.component';
import {OnlyNumberDirective} from './directives/OnlyNumber';



@NgModule({
  declarations: [
    SupportComponent,
    LocationComponent,
    EditCreateComponent,
    OnlyNumberDirective
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
  entryComponents: [EditCreateComponent],
  providers: [MatDialog,
    { provide: MAT_DIALOG_DATA, useValue: {} },
    { provide: MatDialogRef, useValue: {} }]
})
export class MenuModule { }
