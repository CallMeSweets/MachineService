import {Component, HostListener, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-support',
  templateUrl: './support.component.html',
  styleUrls: ['./support.component.scss']
})
export class SupportComponent implements OnInit {

  contactForm: FormGroup;

  contactReasons: string[] = ['gsdg', 'gsdgsd'];

  constructor() {
  }

  ngOnInit() {
  }
}

