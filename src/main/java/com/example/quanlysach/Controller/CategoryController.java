package com.example.quanlysach.Controller;

import com.example.quanlysach.Model.Category;
import com.example.quanlysach.Service.BookService;
import com.example.quanlysach.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BookService bookService;
    @GetMapping("/category")
    public String showCategories(Model model) {
        model.addAttribute("cateEdit", new Category());
        model.addAttribute("category", new Category());
        model.addAttribute("listCategory", categoryService.getAllCategories());
        return "category/categories";
    }
    @PostMapping("/category/create")
    public String create(@Valid Category category, BindingResult res, Model model) {
        if(res.hasErrors()) {
            model.addAttribute("category", category);
            model.addAttribute("listCategory", categoryService.getAllCategories());
            return "category/categories";
        }
        categoryService.addCategory(category.getName());
        return "redirect:/category";

    }
    @PostMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id, Model model) {
            categoryService.remove(id);
            return "redirect:/category";
    }
    @PostMapping("/category/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        var cate = categoryService.getCategory(id);
        model.addAttribute("cateEdit", cate);
        model.addAttribute("category", new Category());
        model.addAttribute("listCategory", categoryService.getAllCategories());
        return "category/categories";
    }
    @PostMapping("category/edit")
    public String edit(Model model, @Valid Category newCategory,BindingResult res) {
        if(res.hasErrors()) {
            model.addAttribute("cateEdit", newCategory);
            model.addAttribute("category", new Category());
            model.addAttribute("listCategory", categoryService.getAllCategories());
            return "category/categories";
        }
        categoryService.updateCategory(newCategory);
        return "redirect:/category";
    }
}
