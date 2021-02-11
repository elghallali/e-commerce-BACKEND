package com.elghallali.ecommercebackend.util;

import com.elghallali.ecommercebackend.security.entity.Role;
import com.elghallali.ecommercebackend.security.enums.RoleName;
import com.elghallali.ecommercebackend.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//**/@Component
public class CreateRoles /**implements CommandLineRunner/**/ {
/*/*
    @Autowired
    RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = new Role(RoleName.ROLE_ADMIN);
        Role roleUser = new Role(RoleName.ROLE_USER);

        roleService.save(roleAdmin);
        roleService.save(roleUser);

    }

 //*/
}
