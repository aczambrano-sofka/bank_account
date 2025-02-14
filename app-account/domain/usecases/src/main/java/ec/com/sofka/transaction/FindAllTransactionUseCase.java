package ec.com.sofka.transaction;

import ec.com.sofka.Transaction;
import ec.com.sofka.gateway.ITransactionrepository;

import java.util.List;

public class FindAllTransactionUseCase {

    private final ITransactionrepository transactionrepository;

    public FindAllTransactionUseCase(ITransactionrepository transactionrepository) {
        this.transactionrepository = transactionrepository;
    }


    public List<Transaction> findAll() {
        return transactionrepository.findAll();
    }


}
