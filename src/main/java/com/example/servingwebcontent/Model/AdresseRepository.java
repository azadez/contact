package com.example.servingwebcontent.Model;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdresseRepository extends CrudRepository<Adresse, Long> {
    List<Adresse> findByville(String ville);
    List<Adresse> findByCP(String CP);

    Adresse findById(long id);
}
