package com.example.dentistmysql.repository;

import java.util.Optional;

import com.example.dentistmysql.model.Users;
import com.example.dentistmysql.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);

    @Query("select u from Users u where u.username = ?1 ")
    public <Users> Users getUserId(String searchTerm);
}
