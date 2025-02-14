package ec.com.sofka.account;

import ec.com.sofka.Account;
import ec.com.sofka.ConflictException;
import ec.com.sofka.gateway.IAccountRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

public class UpdateAccountUseCase {
    private final IAccountRepository accountRepository;

    public UpdateAccountUseCase(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account execute(Account account) {
        Optional<Account> accountOptional = accountRepository.findById(account.getAccountId());

        if (accountOptional.isEmpty()) {
            throw new NoSuchElementException("The account with the given id does not exist.");
        }

        Account existingAccount = accountOptional.get();

        existingAccount.setAccountType(account.getAccountType());
        existingAccount.setInitialBalance(account.getInitialBalance());
        existingAccount.setStatus(account.getStatus());

        return accountRepository.save(existingAccount);
    }

}
