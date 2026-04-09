package com.app.threetier.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class StudentVO {
    private Long id;
    private String studentName;
    private int kor;
    private int eng;
    private int math;
    private int total;
    private double average;
}
