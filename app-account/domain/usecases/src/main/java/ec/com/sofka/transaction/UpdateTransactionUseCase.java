package ec.com.sofka.transaction;

import ec.com.sofka.ConflictException;
import ec.com.sofka.Transaction;
import ec.com.sofka.gateway.ITransactionrepository;

import java.util.Optional;

public class UpdateTransactionUseCase {

    private final ITransactionrepository transactionrepository;

    public UpdateTransactionUseCase(ITransactionrepository transactionrepository) {
        this.transactionrepository = transactionrepository;
    }

    public Transaction execute(Transaction transaction) {
        Optional<Transaction> existingTransactionOpt = transactionrepository.findByAccountId(transaction.getAccountId());

        if (existingTransactionOpt.isEmpty()) {
            throw new ConflictException("The transaction with the given account id does not exist.");
        }

        return transactionrepository.update(transaction);
    }

}
