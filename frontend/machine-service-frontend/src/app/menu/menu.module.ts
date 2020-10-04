import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {SupportComponent} from './contact/support/support.component';
import {ReactiveFormsModule} from '@angular/forms';



@NgModule({
  declarations: [
    SupportComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [
    SupportComponent
  ],
  bootstrap: [SupportComponent]
})
export class MenuModule { }
