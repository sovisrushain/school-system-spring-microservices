import { Component } from '@angular/core';
import {DataServiceService} from "../../service/data/data-service.service";
import {DashBoardCourseData} from "../../model/DashBoardCourseData";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  courseDetailList: DashBoardCourseData[] = []

  constructor(private dataService: DataServiceService) { }

  ngOnInit() {
    this.fetchCourseDetails()
  }

  fetchCourseDetails() {
    this.dataService.getAllCourseDetails().subscribe(
      value => {
        value.map((c: DashBoardCourseData) => this.courseDetailList.push(c))
      })
  }
}
