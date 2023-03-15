package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.model.Producer;
import com.codegym.service.CategoryService;
import com.codegym.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;

@Controller
@RequestMapping("/producers")
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    @GetMapping("")
    public ModelAndView findAll(){
        Iterable<Producer> producers = producerService.findAll();
        ModelAndView modelAndView = new ModelAndView("/producer/list");
        modelAndView.addObject("producers", producers);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView findById(@PathVariable("id") Long id){
        Optional<Producer> producer = producerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/producer/view");
        modelAndView.addObject("producer", producer);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreationForm(){
        ModelAndView modelAndView = new ModelAndView("/producer/create");
        modelAndView.addObject("producer", new Producer());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveCategory(@ModelAttribute("producer") Producer producer ){
        Long randomId = (long) (Math.random() * 10000);
        producer.setId(randomId);//For demo purpose only
        producerService.save(producer);
        ModelAndView modelAndView = new ModelAndView("/producer/list");
        modelAndView.addObject("producer", new Producer());
        modelAndView.addObject("message", "Producer saved successfully");
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditionForm(@PathVariable("id") Long id){
        Optional<Producer> producer = producerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/producer/edit");
        modelAndView.addObject("producer", producer);
        return modelAndView;
    }

    @PostMapping("/{id}/edit")
    public ModelAndView updateCategory(@ModelAttribute("producer") Producer producer ){
        producerService.save(producer);
        ModelAndView modelAndView = new ModelAndView("/producer/edit");
        modelAndView.addObject("producer", producer);
        modelAndView.addObject("message", "Producer updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-producer/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Producer> producer = producerService.findById(id);
        if (producer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/producer/delete");
            modelAndView.addObject("producer", producer.get());
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
       producerService.deleteById(id);
        return "redirect:/producers";
    }

//    @PostMapping("/delete")
//    public String deleteCustomer(@ModelAttribute("category") Category category ) {
//        categoryService.deleteById(category.getId());
//        return "redirect:/categories";
//    }
}
