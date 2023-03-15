package com.codegym.service;

import com.codegym.dto.RoleDto;
import com.codegym.model.Role;
import java.util.List;


public interface RoleService {

    List<RoleDto> getAllRoles();

    Role save(RoleDto roleDto);

    RoleDto getById(Long id);

    void deleteById(Long ids);
}


