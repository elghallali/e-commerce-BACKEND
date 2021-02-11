package com.elghallali.ecommercebackend.security.service;

import com.elghallali.ecommercebackend.security.entity.Role;
import com.elghallali.ecommercebackend.security.enums.RoleName;
import com.elghallali.ecommercebackend.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Optional<Role> getByRoleName(RoleName roleName){
        return roleRepository.findByRoleName(roleName);
    }

    public void save(Role role){
        roleRepository.save(role);
    }
}
