package com.example.jssport_back_end.dto.orders;

public interface IOrderPaymentDto {
    String getAddress();
    Long getPhoneNumber();
    String getName();
    String getProductName();
    String getOrderDate();
    Boolean getPaymentStatus();
    String getNote();
    String getAvatar();
    String getEmail();
    Long getAccountId();
}
