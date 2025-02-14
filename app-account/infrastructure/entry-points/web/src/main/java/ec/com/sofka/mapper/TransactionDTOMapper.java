package ec.com.sofka.mapper;

import ec.com.sofka.Transaction;
import ec.com.sofka.data.TransactionRequestDTO;
import ec.com.sofka.data.TransactionResponseDTO;

public class TransactionDTOMapper {

    public static Transaction toTransaction(TransactionRequestDTO transactionRequestDTO) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(transactionRequestDTO.getAccountId());
        transaction.setTransactionType(transactionRequestDTO.getTransactionType());
        transaction.setValue(transactionRequestDTO.getValue());
        transaction.setBalance(transactionRequestDTO.getBalance());
        return transaction;
    }

    public static TransactionResponseDTO toTransactionResponseDTO(Transaction transaction) {
        return new TransactionResponseDTO(
                transaction.getTransactionId(),
                transaction.getAccountId(),
                transaction.getTransactionType(),
                transaction.getValue(),
                transaction.getBalance()
        );
    }


}
