package ec.com.sofka.database;

import ec.com.sofka.data.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IMySqlTransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    Optional<TransactionEntity> findById(Integer id);
    List<TransactionEntity> findByAccountId(Integer accountId);
    List<TransactionEntity> findAll();
}
