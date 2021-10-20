package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class that holds the data.
 */
@Entity
@Table(name = "ACCOUNT_TRANSACTION")
public class AccountTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SERNO", nullable = false)
    private Long serNo;

    @Column(name = "ACCOUNT_NUMBER", nullable = false)
    private String accountNumber;

    @Column(name = "TRX_AMOUNT")
    private Double trxAmount;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TRX_DATE")
    private String trxDate;

    @Column(name = "TRX_TIME")
    private String trxTime;

    @Column(name = "CUSTOMER_ID")
    private String customerId;

    public AccountTransaction() {

    }

    public AccountTransaction(String accountNumber, Double trxAmount, String description, String trxDate,
            String trxTime, String customerId) {
        super();
        this.accountNumber = accountNumber;
        this.trxAmount = trxAmount;
        this.description = description;
        this.trxDate = trxDate;
        this.trxTime = trxTime;
        this.customerId = customerId;
    }

    public Long getSerNo() {
        return serNo;
    }

    public void setSerNo(Long serNo) {
        this.serNo = serNo;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getTrxAmount() {
        return trxAmount;
    }

    public void setTrxAmount(Double trxAmount) {
        this.trxAmount = trxAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(String trxDate) {
        this.trxDate = trxDate;
    }

    public String getTrxTime() {
        return trxTime;
    }

    public void setTrxTime(String trxTime) {
        this.trxTime = trxTime;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
