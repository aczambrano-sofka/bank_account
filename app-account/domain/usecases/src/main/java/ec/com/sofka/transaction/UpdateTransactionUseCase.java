package ec.com.sofka.transaction;

import ec.com.sofka.ConflictException;
import ec.com.sofka.Transaction;
import ec.com.sofka.gateway.ITransactionrepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

public class UpdateTransactionUseCase {

    private final ITransactionrepository transactionrepository;

    public UpdateTransactionUseCase(ITransactionrepository transactionrepository) {
        this.transactionrepository = transactionrepository;
    }

    public Transaction execute(Transaction transaction) {
        return transactionrepository.findByAccountId(transaction.getAccountId())
                .map(existingTransaction -> {
                    existingTransaction.setTransactionType(transaction.getTransactionType());
                    existingTransaction.setValue(transaction.getValue());
                    existingTransaction.setBalance(transaction.getBalance());
                    existingTransaction.setDate(LocalDateTime.now());
                    return transactionrepository.update(existingTransaction);
                })
                .orElseThrow(() -> new NoSuchElementException("The transaction with the given account id does not exist."));
    }

}
