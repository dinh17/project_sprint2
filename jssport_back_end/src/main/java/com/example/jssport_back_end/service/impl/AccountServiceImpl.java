package com.example.jssport_back_end.service.impl;


import com.example.jssport_back_end.dto.request.ChangePasswordDto;
import com.example.jssport_back_end.model.account.Account;
import com.example.jssport_back_end.repository.IAccountRepository;
import com.example.jssport_back_end.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountRepository iAccountRepository;

    @Override
    public Optional<Account> findByUsername(String username) {
        return iAccountRepository.findByUsername(username);
    }

    @Override
    public Optional<Account> findByUserId(Long accountId) {
        return iAccountRepository.findByUserId(accountId);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(Long accountId) {
        iAccountRepository.save(accountId);
    }

    @Override
    public void changePassword(ChangePasswordDto changePasswordDto) throws Exception {
        Account account = iAccountRepository.findByUserId(changePasswordDto.getAccountId()).orElse(null);
        if (account == null) {
            throw new Exception("Account null");
        }
        account.setPassword(passwordEncoder.encode(changePasswordDto.getNewPass()));
        iAccountRepository.save(account);
    }


    @Override
    public Boolean existsAccountByUsername(String username) {
        return iAccountRepository.existsAccountByUsername(username);
    }

    @Override
    public Boolean existsAccountByEmail(String email) {
        return iAccountRepository.existsAccountByEmail(email);
    }


    @Override
    public void save(Account account) {
        iAccountRepository.save(account);
    }

    @Override
    public void changeAvatar(Long accountId, String ava) {
        iAccountRepository.changeAvatar(accountId,ava);
    }

    @Override
    public void changeInfo(Long accountId, String name, String phoneNumber, String address, String email) {
        iAccountRepository.changeInfo(accountId,name,phoneNumber,address,email);
    }

}
