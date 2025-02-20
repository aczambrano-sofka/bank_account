package ec.com.sofka.account;

import ec.com.sofka.Account;
import ec.com.sofka.data.CustomerInfoRequestRecord;
import ec.com.sofka.gateway.IAccountRepository;
import ec.com.sofka.gateway.IBusMessage;

import java.util.NoSuchElementException;
import java.util.Optional;

public class UpdateAccountUseCase {
    private final IAccountRepository accountRepository;
    private final IBusMessage busMessage;
    public UpdateAccountUseCase(IAccountRepository accountRepository, IBusMessage busMessage) {
        this.accountRepository = accountRepository;
        this.busMessage = busMessage;
    }

    public Account execute(Account account) {

        Optional.ofNullable(busMessage.sendMessage(new CustomerInfoRequestRecord(account.getCustomerId(),false)))
                .filter(Integer.class::isInstance)
                .map(Integer.class::cast)
                .orElseThrow(() -> new IllegalStateException("Customer id not found"));

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
