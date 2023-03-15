package com.codegym.service.impl;

import com.codegym.dto.RoleDto;
import com.codegym.model.Role;
import com.codegym.repository.RoleRepository;
import com.codegym.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository,
                           ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Role save(RoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);
//        Set<Role> roles = new HashSet<>();
//        roles.add(new Role(1L, "ROLE_USER", "Khách hàng"));
//        user.setRoles(roles);
        roleRepository.save(role);
        return  modelMapper.map (role,Role.class);
    }

    @Override
    public List<RoleDto> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(role -> modelMapper.map(role,RoleDto.class))
                .collect(Collectors.toList());
    }




//    @Override
//    public User getById(Long id) {
//        User user = null;
//        if (Objects.nonNull(id)) {
//            Optional<User> optionalUser = userRepository.findById(id);
//            if(optionalUser.isPresent()){
//                user = optionalUser.get();
//            }else{
//                throw new RuntimeException("User not found with the id:"+ id);
//            }
//        }
//        return user;
//    }

    @Override
    public RoleDto getById(Long roleId) {
        Role role = roleRepository.findById(roleId).get();
        return modelMapper.map(role, RoleDto.class);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
