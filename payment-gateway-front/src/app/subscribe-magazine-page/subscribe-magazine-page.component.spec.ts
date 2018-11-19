import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubscribeMagazinePageComponent } from './subscribe-magazine-page.component';

describe('SubscribeMagazinePageComponent', () => {
  let component: SubscribeMagazinePageComponent;
  let fixture: ComponentFixture<SubscribeMagazinePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubscribeMagazinePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubscribeMagazinePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
