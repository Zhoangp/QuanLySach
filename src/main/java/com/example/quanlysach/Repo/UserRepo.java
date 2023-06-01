package com.example.quanlysach.Repo;

import com.example.quanlysach.Model.Book;
import com.example.quanlysach.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    public User getUserByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User getUserByEmail( String email);
}
