import { Component, OnInit } from '@angular/core';
import { VehicleService } from '../vehicle.service';
import { VehicleStats } from '../vehicle.stats';
import { Router, ActivatedRoute } from '../../../node_modules/@angular/router';

@Component({
  selector: 'app-vehicle-overview',
  templateUrl: './vehicle-overview.component.html',
  styleUrls: ['./vehicle-overview.component.css']
})
export class VehicleOverviewComponent implements OnInit {

  public doughnutChartLabels:string[] = ['Offline', 'Online'];
  public doughnutChartData:number[] = [1, 1];
  public doughnutChartType:string = 'pie';

  vehicleStats:VehicleStats;


  constructor(private vehicleService:VehicleService,  private route: ActivatedRoute,
    private router: Router) { }

  getVehicleStats(){
    this.vehicleService.getVehicleStats().subscribe(vehicleStats => { 
      this.vehicleStats = vehicleStats;
      this.doughnutChartData[0] = vehicleStats.offlineCount; 
      this.doughnutChartData[1] = vehicleStats.onlineCount; 
      this.doughnutChartType = 'doughnut';

    })
  }
  ngOnInit() {
    this.getVehicleStats();
  }

  chartClicked(e:any) {
    if (e.active.length > 0) {
      const chart = e.active[0]._chart;
      const activePoints = chart.getElementAtEvent(e.event);
      if ( activePoints.length > 0) {
        // get the internal index of slice in pie chart
        const clickedElementIndex = activePoints[0]._index;
        const label = chart.data.labels[clickedElementIndex];
        // get value by index
        const value = chart.data.datasets[0].data[clickedElementIndex];
        console.log(clickedElementIndex, label, value);
        this.router.navigate(['/vehicle/' + (label).toUpperCase()]);

      }
    }

  }

  chartHovered(e:any) {
    
  }

}
