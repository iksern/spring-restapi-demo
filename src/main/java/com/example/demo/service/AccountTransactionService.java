package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.AccountTransaction;

/**
 * Interface for services of the application
 */
public interface AccountTransactionService {
    List<AccountTransaction> fetchAllAccountTransaction();

    List<AccountTransaction> fetchAllAccountTransactionPageable(Integer pageNo, Integer pageSize, String sortBy);

    List<AccountTransaction> fetchByAccountNumber(String accountNumber, Integer pageNo, Integer pageSize,
            String sortBy);

    List<AccountTransaction> fetchByCustomerId(String customerId, Integer pageNo, Integer pageSize, String sortBy);

    List<AccountTransaction> fetchByDescription(String description, Integer pageNo, Integer pageSize, String sortBy);
}
