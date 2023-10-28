package com.teacher.teacherservice.service;

import com.teacher.teacherservice.dto.TeacherDTO;
import com.teacher.teacherservice.exception.ResourceNotFoundException;
import com.teacher.teacherservice.model.TeacherDAO;
import com.teacher.teacherservice.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    private static final Logger logger = LogManager.getLogger(TeacherServiceImpl.class);

    @Override
    public String saveTeacher(TeacherDTO teacherDTO) {
        logger.info("TeacherServiceImpl.class: saveTeacher(): start");
        TeacherDAO teacher = TeacherDAO.builder()
                .teacherId(teacherDTO.getTeacherId())
                .courseId(teacherDTO.getCourseId())
                .teacherName(teacherDTO.getTeacherName())
                .build();
        teacherRepository.save(teacher);
        return teacher.getTeacherId();
    }

    @Override
    public TeacherDTO getTeacherById(String teacherId) {
        logger.info("TeacherServiceImpl.class: getTeacherById(): start");
        Optional<TeacherDAO> teacher = teacherRepository.findById(teacherId);
        if (teacher.isEmpty()) {
            throw new ResourceNotFoundException("There is no teacher associated with given teacherId");
        }
        return TeacherDTO.builder()
                .teacherId(teacher.get().getTeacherId())
                .courseId(teacher.get().getCourseId())
                .teacherName(teacher.get().getTeacherName())
                .build();
    }

    @Override
    public List<TeacherDTO> getAllTeachers() {
        logger.info("TeacherServiceImpl.class: getAllTeachers(): start");
        List<TeacherDAO> teacherList = teacherRepository.findAll();
        if (teacherList.isEmpty()) {
            throw new ResourceNotFoundException("There are no teachers yet");
        }
        List<TeacherDTO> teacherDTOList = new ArrayList<>();
        teacherList.forEach(teacher -> {
            TeacherDTO dto = new TeacherDTO();
            dto.setTeacherId(teacher.getTeacherId());
            dto.setTeacherName(teacher.getTeacherName());
            dto.setCourseId(teacher.getCourseId());
            teacherDTOList.add(dto);
        });
        return teacherDTOList;
    }

    @Override
    public String deleteTeacherById(String teacherId) {
        logger.info("TeacherServiceImpl.class: deleteTeacherById(): start");
        teacherRepository.deleteById(teacherId);
        return teacherId;
    }
}
