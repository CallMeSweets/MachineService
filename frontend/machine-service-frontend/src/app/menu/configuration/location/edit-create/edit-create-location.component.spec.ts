import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditCreateLocationComponent } from './edit-create-location.component';

describe('EditCreateComponent', () => {
  let component: EditCreateLocationComponent;
  let fixture: ComponentFixture<EditCreateLocationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditCreateLocationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditCreateLocationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
