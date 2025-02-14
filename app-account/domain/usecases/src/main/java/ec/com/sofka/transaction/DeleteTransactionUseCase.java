package ec.com.sofka.transaction;

import ec.com.sofka.ConflictException;
import ec.com.sofka.Transaction;
import ec.com.sofka.gateway.ITransactionrepository;

import java.util.NoSuchElementException;
import java.util.Optional;

public class DeleteTransactionUseCase {

    private final ITransactionrepository transactionrepository;

    public DeleteTransactionUseCase(ITransactionrepository transactionrepository) {
        this.transactionrepository = transactionrepository;
    }


    public void execute(Integer id) {
        transactionrepository.findById(id)
                .ifPresentOrElse(
                        transaction -> transactionrepository.delete(transaction),
                        () -> {
                            throw new NoSuchElementException("The transaction with the given id does not exist.");
                        }
                );
    }


}
