import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContrateumservicoComponent } from './contrateumservico.component';

describe('ContrateumservicoComponent', () => {
  let component: ContrateumservicoComponent;
  let fixture: ComponentFixture<ContrateumservicoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContrateumservicoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContrateumservicoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
