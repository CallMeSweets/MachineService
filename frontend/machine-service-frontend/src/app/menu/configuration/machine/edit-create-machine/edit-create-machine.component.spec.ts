import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditCreateMachineComponent } from './edit-create-machine.component';

describe('EditCreateMachineComponent', () => {
  let component: EditCreateMachineComponent;
  let fixture: ComponentFixture<EditCreateMachineComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditCreateMachineComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditCreateMachineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
