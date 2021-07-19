import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SejaumheroiComponent } from './sejaumheroi.component';

describe('SejaumheroiComponent', () => {
  let component: SejaumheroiComponent;
  let fixture: ComponentFixture<SejaumheroiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SejaumheroiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SejaumheroiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
