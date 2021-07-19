import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DuvidasfrequentesComponent } from './duvidasfrequentes.component';

describe('DuvidasfrequentesComponent', () => {
  let component: DuvidasfrequentesComponent;
  let fixture: ComponentFixture<DuvidasfrequentesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DuvidasfrequentesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DuvidasfrequentesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
