package ec.com.sofka.handler;

import ec.com.sofka.Transaction;
import ec.com.sofka.data.TransactionRequestDTO;
import ec.com.sofka.data.TransactionResponseDTO;
import ec.com.sofka.mapper.TransactionDTOMapper;
import ec.com.sofka.transaction.CreateTransactionUseCase;
import ec.com.sofka.transaction.DeleteTransactionUseCase;
import ec.com.sofka.transaction.FindAllTransactionUseCase;
import ec.com.sofka.transaction.UpdateTransactionUseCase;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionHandler {

    private final CreateTransactionUseCase createTransactionUseCase;
    private final UpdateTransactionUseCase updateTransactionUseCase;
    private final DeleteTransactionUseCase deleteTransactionUseCase;
    private final FindAllTransactionUseCase findAllTransactionUseCase;

    public TransactionHandler(CreateTransactionUseCase createTransactionUseCase, UpdateTransactionUseCase updateTransactionUseCase, DeleteTransactionUseCase deleteTransactionUseCase, FindAllTransactionUseCase findAllTransactionUseCase) {
        this.createTransactionUseCase = createTransactionUseCase;
        this.updateTransactionUseCase = updateTransactionUseCase;
        this.deleteTransactionUseCase = deleteTransactionUseCase;
        this.findAllTransactionUseCase = findAllTransactionUseCase;
    }


    public List<TransactionResponseDTO> getTransactions() {
        return findAllTransactionUseCase.execute().stream()
                .map(TransactionDTOMapper::toTransactionResponseDTO)
                .collect(Collectors.toUnmodifiableList());
    }

    public TransactionResponseDTO createTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        Transaction transaction = TransactionDTOMapper.toTransaction(transactionRequestDTO);
        return TransactionDTOMapper.toTransactionResponseDTO(createTransactionUseCase.execute(transaction));
    }

    public TransactionResponseDTO updateTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        Transaction transaction = TransactionDTOMapper.toTransaction(transactionRequestDTO);
        return TransactionDTOMapper.toTransactionResponseDTO(updateTransactionUseCase.execute(transaction));
    }

    public void deleteTransaction(@PathVariable Integer transactionId) {
        deleteTransactionUseCase.execute(transactionId);
    }

}
