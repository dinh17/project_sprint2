package com.example.jssport_back_end.repository;

import com.example.jssport_back_end.model.account.Role;
import com.example.jssport_back_end.model.account.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
