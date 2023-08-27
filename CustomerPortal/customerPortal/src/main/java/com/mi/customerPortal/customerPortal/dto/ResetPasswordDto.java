package com.mi.customerPortal.customerPortal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordDto {

    private String email;

    private String newPassword;
}
