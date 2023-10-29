import { Component } from '@angular/core';

@Component({
  selector: 'app-save-student',
  templateUrl: './save-student.component.html',
  styleUrls: ['./save-student.component.css']
})
export class SaveStudentComponent {
  categories: string[] = ["C001", "C002", "C003"];
  selectedCategory: string = "";
  studentId: string = ""
  studentName: string = ""

  onSave() {
    // save student
  }
}
