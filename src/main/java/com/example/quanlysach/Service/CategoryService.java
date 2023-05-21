package com.example.quanlysach.Service;

import com.example.quanlysach.Model.Book;
import com.example.quanlysach.Model.Category;
import com.example.quanlysach.Repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    public List<Category> getAllBook() {
        return categoryRepo.findAll();
    }
    public void addCategory(String name) {
        Category category = new Category();
        category.setName(name);
    categoryRepo.save(category);
    }
}
