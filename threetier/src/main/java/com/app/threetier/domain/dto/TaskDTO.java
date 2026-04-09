package com.app.threetier.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class TaskDTO {
    Long id;
    int taskKor;
    int taskEng;
    int taskMath;
}
