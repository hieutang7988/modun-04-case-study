package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ModelAndView findAll(){
        Iterable<Category> categories = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("/category/list");
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView findById(@PathVariable("id") Long id){
        Optional<Category> category = categoryService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/category/view");
        modelAndView.addObject("category", category);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreationForm(){
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveCategory(@ModelAttribute("category") Category category ){
//        Long randomId = (long) (Math.random() * 10000);
//        category.setId(randomId);//For demo purpose only
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/list");
        modelAndView.addObject("category", new Category());
        modelAndView.addObject("message", "Category saved successfully");
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditionForm(@PathVariable("id") Long id){
        Optional<Category> category = categoryService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/category/edit");
        modelAndView.addObject("category", category);
        return modelAndView;
    }

    @PostMapping("/{id}/edit")
    public ModelAndView updateCategory(@ModelAttribute("category") Category category ){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/edit");
        modelAndView.addObject("category", category);
        modelAndView.addObject("message", "Category updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-category/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/category/delete");
            modelAndView.addObject("category", category.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }


//    @PostMapping("/delete")
//    public ModelAndView deleteCategory(@ModelAttribute("category") Category category ){
//        categoryService.deleteById(category.getId());
//        ModelAndView modelAndView = new ModelAndView("/category/delete");
//        modelAndView.addObject("category", category);
//
//        return modelAndView;
//    }

//        @GetMapping("/{id}/delete-category")
//        public String deleteCategory(@PathVariable("id")Long id , Model model){
//        categoryService.deleteById(id);
//        return "redirect:/categories";
//    }

    @PostMapping("/delete")
    public String deleteCategory(Long id){
        categoryService.deleteById(id);
        return "redirect:/categories";
    }

//    @PostMapping("/delete")
//    public String deleteCustomer(@ModelAttribute("category") Category category ) {
//        categoryService.deleteById(category.getId());
//        return "redirect:/categories";
//    }
}
