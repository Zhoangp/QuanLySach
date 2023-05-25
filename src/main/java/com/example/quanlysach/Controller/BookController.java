package com.example.quanlysach.Controller;

import com.example.quanlysach.Model.Book;
import com.example.quanlysach.Service.BookService;
import com.example.quanlysach.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/")
    public String Home(Model model) {
        List<Book> books = bookService.getAllBook();
            model.addAttribute("books", books);
        return "index";
    }
    @GetMapping("/book/create")
    public String CreateBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("listCategory", categoryService.getAllCategories());
        return "book/create";
    }
    @PostMapping("/book/create")
    public String CreateBook(@Valid Book book, BindingResult res, Model model) throws IOException {
        if(res.hasErrors()) {
            model.addAttribute("book", book);
            model.addAttribute("listCategory", categoryService.getAllCategories());
            return "book/create";
        }
        bookService.addBook(book);
        return "redirect:/";
    }
    @PostMapping("/book/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        bookService.removeBook(id);
        return "redirect:/";
    }
    @PostMapping("/book/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        var book = bookService.getBook(id);
        model.addAttribute("book", book);
        model.addAttribute("listCategory", categoryService.getAllCategories());
        return "book/edit";
    }
    @PostMapping("/book/edit")
    public String edit(Model model,@Valid Book newBook, BindingResult res) {
        if(res.hasErrors()) {
            model.addAttribute("book", newBook);
            model.addAttribute("listCategory", categoryService.getAllCategories());
            return "book/edit";
        }
        bookService.update(newBook);
        return "redirect:/";
    }
}
