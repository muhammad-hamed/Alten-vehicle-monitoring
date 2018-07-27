import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { VehicleComponent } from './vehicle/vehicle.component';
import { VehicleDetailComponent } from './vehicle-detail/vehicle-detail.component';
import { CustomerComponent } from './customer/customer.component';
import { CustomerDetailComponent } from './customer-detail/customer-detail.component';
import { CustomerSearchComponent } from './customer-search/customer-search.component';


import { FormsModule }    from '@angular/forms';
import { HttpClientModule }    from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';

import { ChartsModule } from 'ng2-charts';
import { VehicleOverviewComponent } from './vehicle-overview/vehicle-overview.component';


@NgModule({
  declarations: [
    AppComponent,
    VehicleComponent,
    VehicleDetailComponent,
    CustomerComponent,
    CustomerDetailComponent,
    CustomerSearchComponent,
    VehicleOverviewComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    ChartsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
