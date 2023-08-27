package com.mi.customerPortal.customerPortal.respose;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    String message;
    Boolean status;
}
