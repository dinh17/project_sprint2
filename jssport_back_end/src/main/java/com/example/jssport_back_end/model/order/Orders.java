package com.example.jssport_back_end.model.order;

import com.example.jssport_back_end.model.account.Account;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
    @Column(columnDefinition = "bit default 0")
    private Boolean flagDelete;
    //    @Column(columnDefinition = "date")
    private String orderDate;
    @Column(columnDefinition = "TEXT")
    private String address;
    @Column(columnDefinition = "varchar(45)")
    private String phoneNumber;
    @Column(columnDefinition = "LONGTEXT")
    private String note;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;
    @Column(columnDefinition = "bit default 0")
    private Boolean paymentStatus;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "order")
    @JsonBackReference
    private List<PurchaseHistory> purchaseHistoryList;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Boolean getFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(Boolean flagDelete) {
        this.flagDelete = flagDelete;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public List<PurchaseHistory> getPurchaseHistoryList() {
        return purchaseHistoryList;
    }

    public void setPurchaseHistoryList(List<PurchaseHistory> purchaseHistoryList) {
        this.purchaseHistoryList = purchaseHistoryList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
