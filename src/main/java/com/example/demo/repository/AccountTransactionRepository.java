package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.entity.AccountTransaction;

/**
 * Repository interface, with custom query defined.
 */
public interface AccountTransactionRepository
        extends CrudRepository<AccountTransaction, Long>, PagingAndSortingRepository<AccountTransaction, Long> {
    @Query(value = "SELECT t FROM AccountTransaction t WHERE t.accountNumber = :accountNumber", countQuery = "SELECT COUNT(t.serNo) FROM AccountTransaction t WHERE t.accountNumber = :accountNumber")
    Page<AccountTransaction> findByAccountNumber(String accountNumber, Pageable page);

    @Query(value = "SELECT t FROM AccountTransaction t WHERE t.customerId = :customerId", countQuery = "SELECT COUNT(t.serNo) FROM AccountTransaction t WHERE t.customerId = :customerId")
    Page<AccountTransaction> findByCustomerId(String customerId, Pageable page);

    @Query(value = "SELECT t FROM AccountTransaction t WHERE t.description = :description", countQuery = "SELECT COUNT(t.serNo) FROM AccountTransaction t WHERE t.description = :description")
    Page<AccountTransaction> findByDescription(String description, Pageable page);
}
