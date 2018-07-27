import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleOverviewComponent } from './vehicle-overview.component';

describe('VehicleOverviewComponent', () => {
  let component: VehicleOverviewComponent;
  let fixture: ComponentFixture<VehicleOverviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VehicleOverviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehicleOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
