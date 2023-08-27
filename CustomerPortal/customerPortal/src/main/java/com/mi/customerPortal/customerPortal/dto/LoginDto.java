package com.mi.customerPortal.customerPortal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

    private String name;
    private String email;
    private String otp;
}
