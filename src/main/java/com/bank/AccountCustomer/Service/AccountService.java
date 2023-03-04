package com.bank.AccountCustomer.Service;

import com.bank.AccountCustomer.Entity.AccountData;
import com.bank.AccountCustomer.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

//@Service allows to add business functionalities
@Service
public class AccountService
{
    //@Autowired enables to inject the object dependency implicitly
    @Autowired
    private AccountRepository accountRepository;
    public void addAccount(AccountData accountData)
    {
        accountRepository.save(accountData);
    }
    public Optional<AccountData> getAccountById(int id)
    {
        return accountRepository.findById(id);
    }

    public List<AccountData> getAccounts()
    {
        return accountRepository.findAll();
    }

    public AccountData updateAccount(AccountData accountData)
    {
        return accountRepository.save(accountData);
    }

    public String deleteAccountById(int id)
    {
        accountRepository.deleteById(id);
        return "Id " + id + " is deleted";
    }
}
