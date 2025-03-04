package com.springboot.ecommerce.repositories;

import com.springboot.ecommerce.model.AppRole;
import com.springboot.ecommerce.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole appRole);
}
