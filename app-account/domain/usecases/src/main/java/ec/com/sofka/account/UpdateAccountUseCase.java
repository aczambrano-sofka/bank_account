package ec.com.sofka.account;

import ec.com.sofka.Account;
import ec.com.sofka.ConflictException;
import ec.com.sofka.gateway.IAccountRepository;

import java.util.Optional;

public class UpdateAccountUseCase {
    private final IAccountRepository accountRepository;

    public UpdateAccountUseCase(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account execute(Account account) {
        Optional<Account> accountOptional = accountRepository.findById(account.getAccountId());

        if (accountOptional.isEmpty()) {
            throw new ConflictException("The account with the given id does not exist.");
        }
        return accountRepository.save(account);
    }

}
