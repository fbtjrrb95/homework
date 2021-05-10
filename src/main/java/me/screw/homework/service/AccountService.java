package me.screw.homework.service;

import lombok.RequiredArgsConstructor;
import me.screw.homework.domain.Account;
import me.screw.homework.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public long createAccount(Account account){
        Account _account = accountRepository.save(account);
        return _account.getId();
    }

}
