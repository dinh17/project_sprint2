package com.example.jssport_back_end.service;



import com.example.jssport_back_end.model.account.Role;
import com.example.jssport_back_end.model.account.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);

}
