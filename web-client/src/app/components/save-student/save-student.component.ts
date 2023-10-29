import {Component} from '@angular/core';
import {DataServiceService} from "../../service/data/data-service.service";
import {CourseDetailItem} from "../../model/CourseDetailItem";

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

  constructor(private dataService: DataServiceService) {
  }

  ngOnInit() {
    this.fetchAllCourses()
  }

  onSave() {
    // save student
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
