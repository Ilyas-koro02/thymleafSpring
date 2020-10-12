package com.thymeleafSpring.thymleafSpring.repository;

import com.thymeleafSpring.thymleafSpring.entity.Admin;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepo extends CrudRepository<Admin, Integer> {
    Admin getByLoginAndPasswordAdmin (String login, String pass);
}
