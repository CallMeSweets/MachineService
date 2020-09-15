import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TopSiteComponent } from './top-site.component';

describe('TopSiteComponent', () => {
  let component: TopSiteComponent;
  let fixture: ComponentFixture<TopSiteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TopSiteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TopSiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
