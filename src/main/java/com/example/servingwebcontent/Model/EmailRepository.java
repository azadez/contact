package com.example.servingwebcontent.Model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmailRepository extends CrudRepository<Email, Long> {

    Email findById(long id);

    List<Email> findBycustomer(Long id);

    List<Email> findAll();


}