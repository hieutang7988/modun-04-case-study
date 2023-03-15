package com.codegym.service.impl;
import com.codegym.dto.UserDto;

import com.codegym.model.User;
import com.codegym.repository.UserRepository;
import com.codegym.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User save(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
//        Set<Role> roles = new HashSet<>();
//        roles.add(new Role(2, "ROLE_USER", "Khách hàng"));
//        user.setRoles(roles);
        userRepository.save(user);
        return  modelMapper.map (user,User.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
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
    public UserDto getById(Long userId) {
        User user = userRepository.findById(userId).get();
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


}
