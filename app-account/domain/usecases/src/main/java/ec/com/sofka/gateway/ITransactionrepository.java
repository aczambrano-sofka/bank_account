package ec.com.sofka.gateway;

import ec.com.sofka.Transaction;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ITransactionrepository {

    List<Transaction> findAll();
    Optional<Transaction> findById(Integer id);
    Optional<Transaction>  findByAccountId(Integer accountId);
    Transaction save(Transaction transaction);
    Transaction update(Transaction transaction);
    Transaction delete(Transaction transaction);
    List<Transaction> findTransactionsByDateRangeAndCustomer(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("accountId") Integer accountId);
}
