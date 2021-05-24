package com.investment.account.api.repository;

import com.investment.account.model.domain.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

    Account findAccountByAccount(String account);
}
