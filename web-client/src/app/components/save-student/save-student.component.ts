import {Component} from '@angular/core';
import {DataServiceService} from "../../service/data/data-service.service";
import {CourseDetailItem} from "../../model/CourseDetailItem";
import {StudentSaveModel} from "../../model/StudentSaveModel";

@Component({
  selector: 'app-save-student',
  templateUrl: './save-student.component.html',
  styleUrls: ['./save-student.component.css']
})
export class SaveStudentComponent {
  categories: string[] = [];
  selectedCategory: string = "";
  studentId: string = ""
  studentName: string = ""
  student = new StudentSaveModel("", "", "")

  constructor(private dataService: DataServiceService) {
  }

  ngOnInit() {
    this.fetchAllCourses()
  }

  onSave() {
    this.student = new StudentSaveModel(this.studentId, this.studentName, this.selectedCategory)
    this.dataService.postStudent(this.student).subscribe(
      res => {
        this.studentId = ""
        this.studentName = ""
        this.selectedCategory = ""
      }
    )
  }

  fetchAllCourses() {
    this.dataService.getAllCourses().subscribe(
      res => res.map(
        (item: CourseDetailItem) => {
          this.categories.push(item.courseId)
        }
      )
    )
  }
}
