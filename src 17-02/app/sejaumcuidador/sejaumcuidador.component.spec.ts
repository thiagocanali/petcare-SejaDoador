import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SejaumcuidadorComponent } from './sejaumcuidador.component';

describe('SejaumcuidadorComponent', () => {
  let component: SejaumcuidadorComponent;
  let fixture: ComponentFixture<SejaumcuidadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SejaumcuidadorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SejaumcuidadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
