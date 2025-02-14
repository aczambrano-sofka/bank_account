package ec.com.sofka.account;

import ec.com.sofka.Account;
import ec.com.sofka.ConflictException;
import ec.com.sofka.gateway.IAccountRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

public class DeleteAccountUseCase {

    private final IAccountRepository accountRepository;

    public DeleteAccountUseCase(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void execute(Integer accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isEmpty()) {
            throw new NoSuchElementException("The account with the given id does not exist.");
        }

        Account existingAccount = account.get();
        existingAccount.setStatus(false);

        accountRepository.save(existingAccount);
    }

}
