package com.neoris.challenge.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneDTO {
    private Long id;
    private String number;
    private String citycode;
    private String countrycode;
}
