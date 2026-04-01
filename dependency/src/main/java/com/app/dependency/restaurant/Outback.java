package com.app.dependency.restaurant;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Outback implements Restaurant {
    @Override
    public boolean isSaladBar() {
        return false;
    }
}
