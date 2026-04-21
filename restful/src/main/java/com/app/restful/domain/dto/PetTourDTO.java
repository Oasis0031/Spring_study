package com.app.restful.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PetTourDTO {
    private String areaCode;
    private String title;
    private String contentId;
    private String addr1;
    private String addr2;
    private String zipcode;
    private String firstImage;
    private String firstImage2;
    private String tel;
}
