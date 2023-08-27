package com.mi.customerPortal.customerPortal.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
//@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    private long contact;

    private String organization;

    private String otp;

    //private String password;


//    public Customer() {
//    }


}
