package com.example.servingwebcontent.Model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmailRepository extends CrudRepository<Email, Long> {

    Email findById(long id);


    List<Email> findBycustomer(long id);

    List<Email> findAll();


}