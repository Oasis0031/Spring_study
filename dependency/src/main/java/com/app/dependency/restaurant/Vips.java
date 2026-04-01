package com.app.dependency.restaurant;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Vips implements Restaurant{

        private int stakePrice = Restaurant.stakePrice + 30000;

        @Override
        public boolean isSaladBar() {
        return true;
    }
}
