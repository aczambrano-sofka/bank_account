package ec.com.sofka.gateway;

import ec.com.sofka.Transaction;

import java.util.List;
import java.util.Optional;

public interface ITransactionrepository {

    List<Transaction> findAll();
    Optional<Transaction> findById(Integer id);
    Optional<Transaction>  findByAccountId(Integer accountId);
    Transaction save(Transaction transaction);
    Transaction update(Transaction transaction);
    Transaction delete(Transaction transaction);

}
