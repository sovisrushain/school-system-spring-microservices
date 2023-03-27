import React from 'react';
import { NavLink } from 'react-router-dom'
import './Navbar.css';

const Navbar = () => {
    return (
        <nav className="navbar">
            <div className="navbar__logo">SMS</div>
            <ul className="navbar__links">
                <li><NavLink to="/">Home</NavLink></li>
                <li><NavLink to="/student-service">Student</NavLink></li>
                <li><NavLink to="/teacher-service">Teacher</NavLink></li>
                <li><NavLink to="/course-service">Course</NavLink></li>
            </ul>
        </nav>
    );
};

export default Navbar;