package com.elghallali.ecommercebackend.security.repository;

import com.elghallali.ecommercebackend.security.entity.Role;
import com.elghallali.ecommercebackend.security.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}
