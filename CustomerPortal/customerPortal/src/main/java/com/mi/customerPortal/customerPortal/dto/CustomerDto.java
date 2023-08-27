package com.mi.customerPortal.customerPortal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {

    private String name;

    private String email;

    private long contact;

    private String organization;

    private String otp;

}
