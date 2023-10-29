import { Component } from '@angular/core';
import {DataServiceService} from "../../service/data/data-service.service";
import {SearchStudentModel} from "../../model/SearchStudentModel";

@Component({
  selector: 'app-search-student',
  templateUrl: './search-student.component.html',
  styleUrls: ['./search-student.component.css']
})
export class SearchStudentComponent {
  studentId: string = ""

  studentDetail: SearchStudentModel = new SearchStudentModel("", "", "")

  constructor(private dataService: DataServiceService) { }

  onSearch() {
    if (this.studentId != "") {
      this.dataService.getStudentDetail(this.studentId).subscribe(
        res => {
          this.studentDetail.studentName = res.studentName
          this.studentDetail.teacherName = res.teacherName
          this.studentDetail.courseName = res.courseName
        }
      )
    }
  }
}
