import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Vehicle } from '../vehicle';
import { VehicleService } from '../vehicle.service';
import { CustomerService } from '../customer.service';
import { Customer } from '../customer';

@Component({
  selector: 'app-vehicle-detail',
  templateUrl: './vehicle-detail.component.html',
  styleUrls: ['./vehicle-detail.component.css']
})
export class VehicleDetailComponent implements OnInit {
  @Input() vehicle: Vehicle;
  customers: Customer[];


  constructor(
    private route: ActivatedRoute,
    private vehicleService: VehicleService,
    private location: Location,
    private customerService: CustomerService
  ) { }

  ngOnInit(): void {
    this.getVehicle();
    this.getcustomers();
  }

  getVehicle(): void {
    const id = +this.route.snapshot.paramMap.get('id');

    this.vehicleService.getVehicle(id)
      .subscribe(vehicle => this.vehicle = vehicle);
    if (id == 0) {
      this.vehicle = new Vehicle();
    }
  }

  getcustomers(): void {
    this.customerService.getAllCustomers()
    .subscribe(page => this.customers = page.content);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.vehicleService.updateVehicle(this.vehicle)
      .subscribe(() => this.goBack());
  }
}