package com.app.dependency.di;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Login {
    //생성자 주입
    private final Member member;
}
