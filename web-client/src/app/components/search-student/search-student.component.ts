import { Component } from '@angular/core';
import {DataServiceService} from "../../service/data/data-service.service";

@Component({
  selector: 'app-search-student',
  templateUrl: './search-student.component.html',
  styleUrls: ['./search-student.component.css']
})
export class SearchStudentComponent {
  studentId: string = ""
  studentName: string = ""
  teacherName: string = ""
  courseName: string = ""

  constructor(private dataService: DataServiceService) { }

  onSearch() {
    this.dataService.getStudentDetail(this.studentId).subscribe(
      res => {
        this.studentName = res.studentName
        this.teacherName = res.teacherName
        this.courseName = res.courseName
      }
    )
  }
}
