package com.codegym.service;
import com.codegym.dto.UserDto;
import com.codegym.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> getAllUsers();

    User save(UserDto userDto);

    UserDto getById(Long id);

    void deleteById(Long ids);
}


