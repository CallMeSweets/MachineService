import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {DefaultComponent} from './default.component';
import {SharedModule} from '../../shared/shared.module';
import {MatSidenavModule} from '@angular/material';
import {RouterModule} from '@angular/router';



@NgModule({
  declarations: [
    DefaultComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    MatSidenavModule,
    RouterModule
  ],
  exports: [
    DefaultComponent
  ]
})
export class DefaultModule { }
