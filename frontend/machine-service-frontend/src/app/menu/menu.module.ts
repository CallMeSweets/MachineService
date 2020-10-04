import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {SupportComponent} from './contact/support/support.component';
import {ReactiveFormsModule} from '@angular/forms';
import {
  MatButtonModule,
  MatCardModule,
  MatDividerModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule,
  MatSelectModule
} from '@angular/material';
import {FlexModule} from '@angular/flex-layout';



@NgModule({
  declarations: [
    SupportComponent
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
    MatDividerModule
  ],
  exports: [
    SupportComponent
  ],
  bootstrap: [SupportComponent]
})
export class MenuModule { }
