package com.example.jssport_back_end.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IOrderRepository {
    @Modifying
    @Query(value="insert into orders (account_id) values (:accountId) ",nativeQuery = true)
    void createOrder (@Param("accountId")Long accountId);
}
