package ec.com.sofka.account;

import ec.com.sofka.Account;
import ec.com.sofka.ConflictException;
import ec.com.sofka.gateway.IAccountRepository;

import java.util.Optional;

public class CreateAccountUseCase {

    private final IAccountRepository accountRepository;

    public CreateAccountUseCase(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        Optional<Account> accountOptional = accountRepository.findByAccountNumber(account.getAccountNumber());

        if(accountOptional.isPresent()) {
            throw new ConflictException("Account already exists");
        }

        return accountRepository.save(account);
    }


}
