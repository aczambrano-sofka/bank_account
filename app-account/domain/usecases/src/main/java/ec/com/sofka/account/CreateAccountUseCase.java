package ec.com.sofka.account;

import ec.com.sofka.Account;
import ec.com.sofka.gateway.IAccountRepository;

public class CreateAccountUseCase {

    private final IAccountRepository accountRepository;

    public CreateAccountUseCase(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account execute(Account account) {

        accountRepository.findByAccountNumber(account.getAccountNumber())
                .ifPresent(
                        account1 -> {
                            throw new IllegalStateException("Account already exists");
                        }
                );
        return accountRepository.save(account);
    }


}
