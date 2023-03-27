import './App.css';
import Navbar from "./components/navbar/Navbar";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import ManagementService from "./components/management-service/ManagementService";
import StudentService from "./components/student-service/StudentService";
import TeacherService from "./components/teacher-service/TeacherService";
import CourseService from "./components/course-service/CourseService";

function App() {

  return (
    <div className="App">
        <BrowserRouter>
            <Navbar />
            <Routes>
                <Route path="/" element={<ManagementService/>} />
                <Route path="/student-service" element={<StudentService/>} />
                <Route path="/teacher-service" element={<TeacherService/>} />
                <Route path="/course-service" element={<CourseService/>} />
            </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;
