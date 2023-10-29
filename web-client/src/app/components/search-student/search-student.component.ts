import { Component } from '@angular/core';

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

  onSearch() {
    // search
  }
}