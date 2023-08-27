package com.mi.customerPortal.customerPortal.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpUtil {
    public String generateAlphanumericOtp(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder otp = new StringBuilder();

        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            otp.append(characters.charAt(index));
        }

        return otp.toString();
    }

    public  String generateOtp(){
        return generateAlphanumericOtp(6);

    }
}
//        Random random =new Random();
//        int randomNumber = random.nextInt(999999);
//        String output = Integer.toString(randomNumber);
//
//        while (output.length() < 6){
//            output = "0" + output;
//        }
//        return  output;