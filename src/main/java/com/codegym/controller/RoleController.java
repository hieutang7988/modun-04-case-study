package com.codegym.controller;

import com.codegym.dto.RoleDto;
import com.codegym.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("")
    public ModelAndView findAll(){
        Iterable<RoleDto> roleDtos = roleService.getAllRoles();
        ModelAndView modelAndView = new ModelAndView("/roles/list");
        modelAndView.addObject("roleDtos", roleDtos);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView findById(@PathVariable("id") Long id){
        Optional<RoleDto> roleDto = Optional.ofNullable(roleService.getById(id));
        ModelAndView modelAndView = new ModelAndView("/roles/view");
        modelAndView.addObject("roleDto", roleDto);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreationForm(){
        ModelAndView modelAndView = new ModelAndView("/roles/create");
        modelAndView.addObject("roleDto", new RoleDto());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveUser(@ModelAttribute("userDto") RoleDto roleDto ){
//        Long randomId = (long) (Math.random() * 10000);
//        roleDto.setId(randomId);//For demo purpose only
        roleService.save(roleDto);
        ModelAndView modelAndView = new ModelAndView("/roles/list");
        modelAndView.addObject("roleDto", new RoleDto());
        modelAndView.addObject("message", "Role saved successfully");
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditionForm(@PathVariable("id") Long id){
        Optional<RoleDto> roleDto = Optional.ofNullable(roleService.getById(id));
        ModelAndView modelAndView = new ModelAndView("/roles/edit");
        modelAndView.addObject("roleDto", roleDto);
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateCategory(@ModelAttribute("roleDto") RoleDto roleDto ){
        roleService.save(roleDto);
        ModelAndView modelAndView = new ModelAndView("/roles/edit");
        modelAndView.addObject("roleDto", roleDto);
        modelAndView.addObject("message", "Role updated successfully");
        return modelAndView;
    }

    @GetMapping("/{id}/delete-role")
    public ModelAndView showDeleteForm(@PathVariable Long id) {

        Optional<RoleDto> roleDto = Optional.ofNullable(roleService.getById(id));
        if (roleDto.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/roles/delete");
            modelAndView.addObject("roleDto", roleDto.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-role")
    public String deleteRole(Long id){
        roleService.deleteById(id);
        return "redirect:/roles";
    }
}
