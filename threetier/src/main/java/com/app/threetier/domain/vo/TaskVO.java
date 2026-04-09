package com.app.threetier.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class TaskVO {
    Long id;
    int taskKor;
    int taskEng;
    int taskMath;
    int total;
    int average;

}
