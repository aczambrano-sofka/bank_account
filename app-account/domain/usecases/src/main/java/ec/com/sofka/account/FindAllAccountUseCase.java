package ec.com.sofka.account;

import ec.com.sofka.Account;
import ec.com.sofka.gateway.IAccountRepository;

import java.util.List;

public class FindAllAccountUseCase {

    private final IAccountRepository accountRepository;

    public FindAllAccountUseCase(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> execute() {
        return accountRepository.findAll();
    }

}
