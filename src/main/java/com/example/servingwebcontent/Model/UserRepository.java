package com.example.servingwebcontent.Model;


import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String login);

}
