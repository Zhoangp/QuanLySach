package com.example.quanlysach.Service;

import com.example.quanlysach.Model.Book;
import com.example.quanlysach.Repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;
    public List<Book> getAllBook() {
        return bookRepo.findAll();
    }
    public Book getBook(Long id) {
        return bookRepo.findById(id).orElse(null);
    }
    public void addBook(Book book) {
        bookRepo.save(book);
    }
    public void removeBook(Long id) {
        bookRepo.deleteById(id);
    }
    public void update(Book newBook) {
        var book = getBook(newBook.getId());
        book.setAuthor(newBook.getAuthor());
        book.setCategory(newBook.getCategory());
        book.setTitle(newBook.getTitle());
        book.setPrice(newBook.getPrice());
        bookRepo.save(book);
    }
}
