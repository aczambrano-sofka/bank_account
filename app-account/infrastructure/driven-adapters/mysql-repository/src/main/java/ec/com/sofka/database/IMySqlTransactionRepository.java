package ec.com.sofka.database;

import ec.com.sofka.data.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IMySqlTransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    Optional<TransactionEntity> findById(Integer id);
    List<TransactionEntity> findByAccount_AccountId(Integer accountId);
    List<TransactionEntity> findAll();

    @Query("SELECT m FROM TransactionEntity m WHERE m.date BETWEEN :startDate AND :endDate " +
            "AND m.account .accountId= :accountId " +
            "ORDER BY m.date DESC")

    List<TransactionEntity> findTransactionsByDateRangeAndCustomer(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("accountId") Integer accountId);
}
