package com.teacher.teacherservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
    private Date timeStamp;
    private int statusCode;
    private String message;
    private String description;
}
