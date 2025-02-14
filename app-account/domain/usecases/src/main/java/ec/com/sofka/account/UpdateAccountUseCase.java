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
        return accountRepository.findById(account.getAccountId())
                .map(existingAccount -> {
                            existingAccount.setAccountType(account.getAccountType());
                            existingAccount.setInitialBalance(account.getInitialBalance());
                            existingAccount.setStatus(account.getStatus());
                            return accountRepository.save(existingAccount);
                        })
                .orElseThrow(() -> new NoSuchElementException("The account with the given id does not exist."));
    }

}
