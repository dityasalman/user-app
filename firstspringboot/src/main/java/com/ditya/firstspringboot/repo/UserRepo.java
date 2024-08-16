package com.ditya.firstspringboot.repo;

import com.ditya.firstspringboot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Long> {
}
