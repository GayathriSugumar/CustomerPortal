package com.mi.customerPortal.customerPortal.controller;

import com.mi.customerPortal.customerPortal.dto.CustomerDto;
import com.mi.customerPortal.customerPortal.dto.ForgotPasswordDto;
import com.mi.customerPortal.customerPortal.dto.LoginDto;
import com.mi.customerPortal.customerPortal.dto.ResetPasswordDto;
import com.mi.customerPortal.customerPortal.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        System.out.println("Inside CustomerController");
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody CustomerDto customerDto) {
        System.out.println("Inside Register mapping");
        return new ResponseEntity<>(customerService.register(customerDto), HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDto loginDto) {
        System.out.println("Inside login mapping");
        return new ResponseEntity<>(customerService.login(loginDto), HttpStatus.OK);

    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordDto forgotPasswordDto) {
        return new ResponseEntity<>(customerService.requestPasswordReset(forgotPasswordDto.getEmail()), HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        return new ResponseEntity<>(customerService.resetPassword(resetPasswordDto), HttpStatus.OK);
    }


}
