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

        accountRepository.findById(accountId)
                .map(account -> {
                    account.setStatus(false);
                    return accountRepository.save(account);
                })
                .orElseThrow(() -> new NoSuchElementException("The account with the given id does not exist."));
    }
}
