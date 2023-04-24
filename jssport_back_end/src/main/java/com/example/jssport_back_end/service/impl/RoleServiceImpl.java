package com.example.jssport_back_end.service.impl;

import com.example.jssport_back_end.model.account.Role;
import com.example.jssport_back_end.model.account.RoleName;
import com.example.jssport_back_end.repository.IRoleRepository;
import com.example.jssport_back_end.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleRepository iRoleRepository;


    @Override
    public Optional<Role> findByName(RoleName name) {
        return iRoleRepository.findByName(name);
    }
}
