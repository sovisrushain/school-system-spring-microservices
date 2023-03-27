import {useState} from "react";
import axios from 'axios';
import './ManagementService.css'
import Student from "../../model/Student";

const ManagementService = () => {

    const [searchId, setSearchId] = useState('');
    const [student, setStudent] = useState<Student>();

    const handleSearch = async () => {
        const response = await axios.get(`http://localhost:8084/management-service/api/v1/management/${searchId}`);
        setStudent(response.data);
    };

    return (
        <div className="student-search">
            <div className="student-search__search-box">
                <label className="student-search__label">
                    Search by ID:
                    <input
                        type="text"
                        value={searchId}
                        onChange={(e) => setSearchId(e.target.value)}
                        className="student-search__input"
                    />
                </label>
                <button onClick={handleSearch} className="student-search__button">
                    Search
                </button>
            </div>

            {student && (
                <div className="student-search__student-details">
                    <p className="student-search__heading">{student.studentName}</p>
                    <p className="student-search__detail">Teacher: {student.teacherName}</p>
                    <p className="student-search__detail">Course: {student.courseName}</p>
                </div>
            )}
        </div>
    )
}

export default ManagementService;