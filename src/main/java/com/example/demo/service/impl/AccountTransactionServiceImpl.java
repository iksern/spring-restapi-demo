package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AccountTransaction;
import com.example.demo.repository.AccountTransactionRepository;
import com.example.demo.service.AccountTransactionService;

/**
 * Implementation of {@link AccountTransactionService}
 */
@Service
public class AccountTransactionServiceImpl implements AccountTransactionService {

    private AccountTransactionRepository accountTransactionRepository;

    public AccountTransactionServiceImpl(AccountTransactionRepository accountTransactionRepository) {
        super();
        this.accountTransactionRepository = accountTransactionRepository;
    }

    @Override
    public List<AccountTransaction> fetchAllAccountTransaction() {
        return (List<AccountTransaction>) accountTransactionRepository.findAll();
    }

    @Override
    public List<AccountTransaction> fetchAllAccountTransactionPageable(Integer pageNo, Integer pageSize,
            String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<AccountTransaction> pagedResult = accountTransactionRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<AccountTransaction>();
        }
    }

    @Override
    public List<AccountTransaction> fetchByAccountNumber(String accountNumber, Integer pageNo, Integer pageSize,
            String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<AccountTransaction> pagedResult = accountTransactionRepository.findByAccountNumber(accountNumber, paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<AccountTransaction>();
        }
    }

    @Override
    public List<AccountTransaction> fetchByCustomerId(String customerId, Integer pageNo, Integer pageSize,
            String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<AccountTransaction> pagedResult = accountTransactionRepository.findByCustomerId(customerId, paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<AccountTransaction>();
        }
    }

    @Override
    public List<AccountTransaction> fetchByDescription(String description, Integer pageNo, Integer pageSize,
            String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<AccountTransaction> pagedResult = accountTransactionRepository.findByDescription(description, paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<AccountTransaction>();
        }
    }
}
