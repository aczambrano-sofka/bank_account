package ec.com.sofka.mapper;

import ec.com.sofka.Transaction;
import ec.com.sofka.data.AccountEntity;
import ec.com.sofka.data.TransactionEntity;

public class TransactionMapper {

    public static Transaction entityToTransaction(TransactionEntity transactionEntity) {
        if (transactionEntity == null) {
            return null;
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionEntity.getTransactionId());
        transaction.setDate(transactionEntity.getDate());
        transaction.setTransactionType(transactionEntity.getTransactionType());
        transaction.setValue(transactionEntity.getValue());
        transaction.setBalance(transactionEntity.getBalance());
        transaction.setAccountId(transactionEntity.getAccount().getAccountId());
        return transaction;
    }

    public static TransactionEntity transactionToEntity(Transaction transaction) {
        if (transaction == null) {
            return null;
        }

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionId(transaction.getTransactionId());
        transactionEntity.setDate(transaction.getDate());
        transactionEntity.setTransactionType(transaction.getTransactionType());
        transactionEntity.setValue(transaction.getValue());
        transactionEntity.setBalance(transaction.getBalance());
        transactionEntity.setAccount(new AccountEntity(transaction.getAccountId()));

        return transactionEntity;
    }

}
