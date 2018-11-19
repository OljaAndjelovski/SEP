import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChoosePaymentPageComponent } from './choose-payment-page.component';

describe('ChoosePaymentPageComponent', () => {
  let component: ChoosePaymentPageComponent;
  let fixture: ComponentFixture<ChoosePaymentPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChoosePaymentPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChoosePaymentPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
