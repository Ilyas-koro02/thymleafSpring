package com.thymeleafSpring.thymleafSpring.repository;

import com.thymeleafSpring.thymleafSpring.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<Users, Integer> {
    List<Users> findAll();
}
