package com.example.servingwebcontent.Model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);

    List<Customer> findAll();
    Customer findById(long id);


}
