package com.example.servingwebcontent.Model;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdresseRepository extends CrudRepository<Adresse, Long> {
    List<Adresse> findAll();

    Adresse findById(long id);
}
