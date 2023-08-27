package com.mi.customerPortal.customerPortal.service;

import com.mi.customerPortal.customerPortal.dto.CustomerDto;
import com.mi.customerPortal.customerPortal.dto.LoginDto;
import com.mi.customerPortal.customerPortal.dto.ResetPasswordDto;
import com.mi.customerPortal.customerPortal.entity.Customer;
import com.mi.customerPortal.customerPortal.repository.CustomerRepository;
import com.mi.customerPortal.customerPortal.respose.LoginResponse;
import com.mi.customerPortal.customerPortal.util.EmailUtil;
import com.mi.customerPortal.customerPortal.util.OtpUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private OtpUtil otpUtil;

    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepository customerRepository;

    public String register(CustomerDto customerDto) {
        System.out.println("Inside Registration service");
        String otp = otpUtil.generateOtp();

        try {
            emailUtil.sendOtpEmail(customerDto.getEmail(), otp);
        } catch (MessagingException e) {
            System.out.println("Invalid Email");
            throw new RuntimeException(e);
        }
        // Encode the OTP using the password encoder
        String encodedOtp = passwordEncoder.encode(otp);

        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setContact(customerDto.getContact());
        customer.setOrganization(customerDto.getOrganization());
        customer.setOtp(encodedOtp); // Set the encoded password

        customerRepository.save(customer);
        return "User registration successful";
    }

    public LoginResponse login(LoginDto loginDto) {
        System.out.println("Inside login service");
        String msg = "";
        Customer customer = customerRepository.findByNameAndEmail(loginDto.getName(), loginDto.getEmail());
        if (customer != null) {
            String otp = loginDto.getOtp();
            String encodedPassword = customer.getOtp();
            Boolean isPwdRight = passwordEncoder.matches(otp, encodedPassword);
            if (isPwdRight) {
                List<Customer> customersWithMatchingNameEmailAndOtp = customerRepository.findAllByNameAndEmailAndOtp(loginDto.getName(), loginDto.getEmail(), encodedPassword);
                if (customersWithMatchingNameEmailAndOtp.isEmpty()) {
                    return new LoginResponse("Login Failed", false);
                } else if (customersWithMatchingNameEmailAndOtp.size() == 1) {
                    return new LoginResponse("Login Success", true);
                } else {
                    return new LoginResponse("Unexpected error: Multiple matching records found", false);
                }
            }
        }
        // Handle cases where customer is null, or password doesn't match, or name and email don't match
        return new LoginResponse("Login Failed", false);
    }

    public String requestPasswordReset(String email) {
        System.out.println("Inside requestPasswordReset service");
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null) {
            String resetToken = otpUtil.generateOtp();
            customerRepository.save(customer);

            try {
                emailUtil.sendResetPasswordEmail(email, resetToken);
                return "Password reset email sent successfully";
            } catch (MessagingException e) {
                throw new RuntimeException("Failed to send reset email");
            }
        }
        return "User not found";
    }


    public String resetPassword(ResetPasswordDto resetPasswordDto) {
        System.out.println("Inside resetPassword service");
        String newPassword = resetPasswordDto.getNewPassword();
        String userEmail = resetPasswordDto.getEmail();

        Customer customer = customerRepository.findByEmail(userEmail);
        if (customer != null) {
            // Update the password
            customer.setOtp(passwordEncoder.encode(newPassword));
            customerRepository.save(customer);
            return "Password reset successful";
        }
        return "User not found";
    }

}