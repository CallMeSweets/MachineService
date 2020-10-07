import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {DefaultComponent} from './layouts/default/default.component';
import {SupportComponent} from './menu/contact/support/support.component';
import {LocationComponent} from './menu/configuration/location/location.component';

const routes: Routes = [{
  path: '', component: DefaultComponent,
  children: [
    {path: 'support', component: SupportComponent},
    {path: 'locations', component: LocationComponent}
  ]
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
