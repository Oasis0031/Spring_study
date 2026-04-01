package com.app.dependency.qualifier;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Labtop implements Computer{

    @Override
    public int getScreenSize() {
        return 1908;
    }
}
