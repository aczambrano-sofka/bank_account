package ec.com.sofka.adapter;

import ec.com.sofka.Transaction;
import ec.com.sofka.data.TransactionEntity;
import ec.com.sofka.database.IMySqlTransactionRepository;
import ec.com.sofka.gateway.ITransactionrepository;
import ec.com.sofka.mapper.TransactionMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TransactionAdapter implements ITransactionrepository {

    private final IMySqlTransactionRepository transactionRepository;

    public TransactionAdapter(IMySqlTransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    @Override
    public List<Transaction> findAll() {
        List<TransactionEntity> transactionEntities = transactionRepository.findAll();
        return transactionEntities.stream()
                .map(TransactionMapper::entityToTransaction)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Transaction> findById(Integer id) {
        Optional<TransactionEntity> transactionEntity = transactionRepository.findById(id);
        return transactionEntity.map(TransactionMapper::entityToTransaction);
    }

    @Override
    public Optional<Transaction> findByAccountId(Integer accountId) {
        List<TransactionEntity> transactionEntities = transactionRepository.findByAccountId(accountId);
        if (transactionEntities.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(TransactionMapper.entityToTransaction(transactionEntities.get(0)));
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity transactionEntity = TransactionMapper.transactionToEntity(transaction);
        TransactionEntity savedEntity = transactionRepository.save(transactionEntity);
        return TransactionMapper.entityToTransaction(savedEntity);
    }

    @Override
    public Transaction update(Transaction transaction) {
        Optional<TransactionEntity> existingEntity = transactionRepository.findById(transaction.getTransactionId());

        if (existingEntity.isPresent()) {
            TransactionEntity updatedEntity = existingEntity.get();
            updatedEntity.setDate(transaction.getDate());
            updatedEntity.setTransactionType(transaction.getTransactionType());
            updatedEntity.setValue(transaction.getValue());
            updatedEntity.setBalance(transaction.getBalance());
            updatedEntity.setAccountId(transaction.getAccountId());

            transactionRepository.save(updatedEntity);
            return TransactionMapper.entityToTransaction(updatedEntity);
        } else {
            throw new RuntimeException("Transaction not found");
        }
    }

    @Override
    public Transaction delete(Transaction transaction) {
        Optional<TransactionEntity> existingEntity = transactionRepository.findById(transaction.getTransactionId());

        if (existingEntity.isPresent()) {
            transactionRepository.delete(existingEntity.get());
            return transaction;
        } else {
            throw new RuntimeException("Transaction not found");
        }
    }
}
