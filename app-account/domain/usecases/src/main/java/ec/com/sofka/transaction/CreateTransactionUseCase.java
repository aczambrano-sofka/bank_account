package ec.com.sofka.transaction;

import ec.com.sofka.ConflictException;
import ec.com.sofka.Transaction;
import ec.com.sofka.gateway.ITransactionrepository;

import java.util.Optional;

public class CreateTransactionUseCase {

    private final ITransactionrepository transactionrepository;

    public CreateTransactionUseCase(ITransactionrepository transactionrepository) {
        this.transactionrepository = transactionrepository;
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionrepository.save(transaction);
    }

}
