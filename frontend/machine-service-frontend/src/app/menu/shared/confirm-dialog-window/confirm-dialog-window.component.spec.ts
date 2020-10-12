import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmDialogWindowComponent } from './confirm-dialog-window.component';

describe('ConfirmDialogWindowComponent', () => {
  let component: ConfirmDialogWindowComponent;
  let fixture: ComponentFixture<ConfirmDialogWindowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfirmDialogWindowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmDialogWindowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
