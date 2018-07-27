import { CustomerComponent } from "./customer/customer.component";
import { CustomerDetailComponent } from "./customer-detail/customer-detail.component";
import { RouterModule, Routes } from "@angular/router";
import { NgModule } from "@angular/core";
import { CustomerSearchComponent } from "./customer-search/customer-search.component";
import { VehicleOverviewComponent } from "./vehicle-overview/vehicle-overview.component";
import { VehicleDetailComponent } from "./vehicle-detail/vehicle-detail.component";
import { VehicleComponent } from "./vehicle/vehicle.component";

const routes: Routes = [
    { path: '', redirectTo: '/vehicleoverview', pathMatch: 'full' },
    { path: 'vehicleoverview', component: VehicleOverviewComponent },
    { path: 'customer', component: CustomerComponent },
    { path: 'customer/:page', component: CustomerComponent },
    { path: 'custdetail/:id', component: CustomerDetailComponent },
    { path: 'vehicle', component: VehicleComponent },
    { path: 'vehicle/:status', component: VehicleComponent },
    { path: 'vehicledetail/:id', component: VehicleDetailComponent },
    { path: 'search', component: CustomerSearchComponent }
   
  ];
  
  @NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
  })
  export class AppRoutingModule {}