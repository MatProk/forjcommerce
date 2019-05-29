package com.jcommerce.cars.repository;

import com.jcommerce.cars.domain.Role;
import com.jcommerce.cars.domain.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName roleName);
}