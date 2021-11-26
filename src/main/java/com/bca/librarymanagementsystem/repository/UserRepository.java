package com.bca.librarymanagementsystem.repository;

import com.bca.librarymanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
