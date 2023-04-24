package com.example.jssport_back_end.service;



import com.example.jssport_back_end.dto.request.ChangePasswordDto;
import com.example.jssport_back_end.model.account.Account;

import java.util.Optional;

public interface IAccountService {
    Optional<Account> findByUsername(String username);

    Optional<Account> findByUserId(Long accountId);

    void save(Long accountId);

    void changePassword(ChangePasswordDto changePasswordDto) throws Exception;

    Boolean existsAccountByUsername(String username);

    Boolean existsAccountByEmail( String email);

    void save(Account account);

    void changeAvatar(Long accountId, String ava);

    void changeInfo(Long accountId,String name,
                    String phoneNumber,String address,String email);

}
