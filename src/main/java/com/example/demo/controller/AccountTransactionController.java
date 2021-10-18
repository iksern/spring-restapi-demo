package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AccountTransaction;
import com.example.demo.service.AccountTransactionService;

/**
 * Main class for REST controller to process REST request
 */
@RestController
public class AccountTransactionController {
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    private AccountTransactionService accountTransactionService;

    public AccountTransactionController(AccountTransactionService accountTransactionService) {
        super();
        this.accountTransactionService = accountTransactionService;
    }

    @RequestMapping(value = "/accountTransactions", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<AccountTransaction> getAccountTransactions() {
        List<AccountTransaction> listOfAccountTransactions = new ArrayList<>();
        listOfAccountTransactions = accountTransactionService.fetchAllAccountTransaction();
        return listOfAccountTransactions;
    }

    @RequestMapping(value = "/accountTransactions/showAll", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<AccountTransaction>> getAccountTransactionsPageable(
            @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "serNo") String sortBy) {
        List<AccountTransaction> list = accountTransactionService.fetchAllAccountTransactionPageable(pageNo, pageSize,
                sortBy);
        return new ResponseEntity<List<AccountTransaction>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/accountTransactions/account/{accountNumber}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<AccountTransaction>> getAccountTransactionsByAccountNumber(
            @PathVariable String accountNumber, @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "serNo") String sortBy) {
        List<AccountTransaction> list = accountTransactionService.fetchByAccountNumber(accountNumber, pageNo, pageSize,
                sortBy);
        return new ResponseEntity<List<AccountTransaction>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/accountTransactions/customer/{customerId}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<AccountTransaction>> getAccountTransactionsByCustomerId(@PathVariable String customerId,
            @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "serNo") String sortBy) {
        List<AccountTransaction> list = accountTransactionService.fetchByCustomerId(customerId, pageNo, pageSize,
                sortBy);
        return new ResponseEntity<List<AccountTransaction>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/accountTransactions/description/{description}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<AccountTransaction>> getAccountTransactionsByDescription(
            @PathVariable String description, @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "serNo") String sortBy) {
        List<AccountTransaction> list = accountTransactionService.fetchByDescription(description, pageNo, pageSize,
                sortBy);
        return new ResponseEntity<List<AccountTransaction>>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
