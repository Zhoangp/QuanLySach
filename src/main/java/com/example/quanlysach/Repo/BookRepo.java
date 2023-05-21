package com.example.quanlysach.Repo;

import com.example.quanlysach.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Long> {
}
