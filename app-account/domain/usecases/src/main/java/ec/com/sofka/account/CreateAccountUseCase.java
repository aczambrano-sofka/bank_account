package ec.com.sofka.account;

import ec.com.sofka.Account;
import ec.com.sofka.ConflictException;
import ec.com.sofka.data.CustomerInfoRequestRecord;
import ec.com.sofka.gateway.IAccountRepository;
import ec.com.sofka.gateway.IBusMessage;

import java.util.NoSuchElementException;
import java.util.Optional;

public class CreateAccountUseCase {

    private final IAccountRepository accountRepository;
    private final IBusMessage busMessage;

    public CreateAccountUseCase(IAccountRepository accountRepository, IBusMessage busMessage) {
        this.accountRepository = accountRepository;
        this.busMessage = busMessage;
    }

    public Account execute(Account account) {

        accountRepository.findByAccountNumber(account.getAccountNumber())
                .ifPresent(
                        account1 -> {
                            throw new ConflictException("Account already exists");
                        }
                );

        Optional.ofNullable(busMessage.sendMessage(new CustomerInfoRequestRecord(account.getCustomerId(),false)))
                .filter(Integer.class::isInstance)
                .map(Integer.class::cast)
                .orElseThrow(() -> new NoSuchElementException("Customer id not found"));

        return accountRepository.save(account);
    }


}
