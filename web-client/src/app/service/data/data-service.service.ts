import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CourseDetailItem} from "../../model/CourseDetailItem";
import {StudentSaveModel} from "../../model/StudentSaveModel";

@Injectable({
  providedIn: 'root'
})
export class DataServiceService {

  constructor(private http: HttpClient) { }

  getAllCourseDetails() {
    return this.http.get<any>("http://localhost:8084/management-service/api/v1/management/course-details")
  }

  getStudentDetail(studentId: string) {
    return this.http.get<any>("http://localhost:8084/management-service/api/v1/management/" + studentId)
  }

  getAllCourses() {
    return this.http.get<CourseDetailItem[]>("http://localhost:8084/course-service/api/v1/course/")
  }

  postStudent(student: StudentSaveModel) {
    return this.http.post("http://localhost:8084/management-service/api/v1/management/save-student", student)
  }
}
