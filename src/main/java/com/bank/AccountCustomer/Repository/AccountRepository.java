package com.bank.AccountCustomer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bank.AccountCustomer.Entity.AccountData;

@Repository
public interface AccountRepository extends JpaRepository<AccountData,Integer>
{
}