package com.codegym.controller;

import com.codegym.dto.UserDto;

import com.codegym.service.SecurityService;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("")
    public ModelAndView findAll(){
        Iterable<UserDto> userDtos = userService.getAllUsers();
        ModelAndView modelAndView = new ModelAndView("/users/list");
        modelAndView.addObject("userDtos", userDtos);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView findById(@PathVariable("id") Long id){
        Optional<UserDto> userDto = Optional.ofNullable(userService.getById(id));
        ModelAndView modelAndView = new ModelAndView("/users/view");
        modelAndView.addObject("userDto", userDto);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreationForm(){
        ModelAndView modelAndView = new ModelAndView("/users/create");
        modelAndView.addObject("userDto", new UserDto());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveUser(@ModelAttribute("userDto") UserDto userDto ){
        Long randomId = (long) (Math.random() * 10000);
        userDto.setId(randomId);//For demo purpose only
        userService.save(userDto);
        ModelAndView modelAndView = new ModelAndView("/users/list");
        modelAndView.addObject("userDto", new UserDto());
        modelAndView.addObject("message", "Category saved successfully");
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditionForm(@PathVariable("id") Long id){
        Optional<UserDto> userDto = Optional.ofNullable(userService.getById(id));
        ModelAndView modelAndView = new ModelAndView("/users/edit");
        modelAndView.addObject("userDto", userDto);
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateCategory(@ModelAttribute("userDto") UserDto userDto ){
        userService.save(userDto);
        ModelAndView modelAndView = new ModelAndView("/users/edit");
        modelAndView.addObject("userDto", userDto);
        modelAndView.addObject("message", "User updated successfully");
        return modelAndView;
    }

    @GetMapping("/{id}/delete-user")
    public ModelAndView showDeleteForm(@PathVariable Long id) {

        Optional<UserDto> userDto = Optional.ofNullable(userService.getById(id));
        if (userDto.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/users/delete");
            modelAndView.addObject("userDto", userDto.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

//    @PostMapping("/delete-user")
//    public UserDto deleteCategory(@ModelAttribute("userDto") UserDto userDto) {
//        Optional<UserDto> userDtoOptional = Optional.ofNullable(userService.getById(userDto.getId()));
//        userService.deleteById(userDtoOptional.get().getId());
//        return (userDtoOptional.get());
//    }

    @PostMapping("/delete-user")
    public String deleteRole(Long id){
        userService.deleteById(id);
        return "redirect:/users";
    }
}
