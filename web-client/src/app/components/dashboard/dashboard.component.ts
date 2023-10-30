import { Component } from '@angular/core';
import {DataServiceService} from "../../service/data/data-service.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  constructor(private dataService: DataServiceService) { }

  onOnInit() {
    this.fetchCourseDetails()
  }

  fetchCourseDetails() {
    this.dataService.getAllCourseDetails().subscribe(
      response => {
      console.log(response)
    })
  }
}
