package com.mi.customerPortal.customerPortal.repository;

import com.mi.customerPortal.customerPortal.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByNameAndEmail(String name, String email);

    List<Customer> findAllByNameAndEmailAndOtp(String name, String email, String otp);

    Customer findByEmail(String email);


}
