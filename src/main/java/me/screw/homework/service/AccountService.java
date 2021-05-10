package me.screw.homework.service;

import lombok.RequiredArgsConstructor;
import me.screw.homework.domain.Account;
import me.screw.homework.repository.AccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public long createAccount(Account account){
        Account _account = accountRepository.save(account);
        return _account.getId();
    }

    public Optional<Account> findAccount(long id){
        return accountRepository.findById(id);
    }

    public Page<Account> showAccountList(Pageable pageable){
        return accountRepository.findAll(pageable);
    }

}
