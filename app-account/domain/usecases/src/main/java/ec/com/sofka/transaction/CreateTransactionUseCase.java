package ec.com.sofka.transaction;

import ec.com.sofka.Account;
import ec.com.sofka.ConflictException;
import ec.com.sofka.Transaction;
import ec.com.sofka.gateway.IAccountRepository;
import ec.com.sofka.gateway.ITransactionrepository;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

public class CreateTransactionUseCase {

    private final ITransactionrepository transactionrepository;
    private final IAccountRepository accountRepository;

    public CreateTransactionUseCase(ITransactionrepository transactionrepository, IAccountRepository accountRepository) {
        this.transactionrepository = transactionrepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Transaction execute(Transaction transaction) {

        Account account = accountRepository.findById(transaction.getAccountId())
                .orElseThrow(() -> new NoSuchElementException("The account with the given id does not exist."));

        BigDecimal newBalance = account.getInitialBalance().add(transaction.getValue());

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new ConflictException("Balance not available");
        }

        transaction.setDate(LocalDateTime.now());
        transaction.setBalance(newBalance);
        Transaction savedTransaction = transactionrepository.save(transaction);

        account.setInitialBalance(newBalance);
        accountRepository.save(account);

        return savedTransaction;
    }

}
