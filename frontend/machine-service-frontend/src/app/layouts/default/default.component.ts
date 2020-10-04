import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-default',
  templateUrl: './default.component.html',
  // tslint:disable-next-line:object-literal-sort-keys
  styleUrls: ['./default.component.scss'],
})
export class DefaultComponent implements OnInit {
  isSidebarHidden = true;

  constructor() { }

  ngOnInit(): void {
  }

  toggleSideBar() {
    this.isSidebarHidden = !this.isSidebarHidden;
  }
}
