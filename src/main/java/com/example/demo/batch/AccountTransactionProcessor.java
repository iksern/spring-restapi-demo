package com.example.demo.batch;

import com.example.demo.entity.AccountTransaction;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * Item processor to transform the given entity
 */
@Component
public class AccountTransactionProcessor implements ItemProcessor<AccountTransaction, AccountTransaction> {

    @Override
    public AccountTransaction process(AccountTransaction accountTransaction) throws Exception {
        return accountTransaction;
    }
}
