package ec.com.sofka.controller;


import ec.com.sofka.data.TransactionRequestDTO;
import ec.com.sofka.data.TransactionResponseDTO;
import ec.com.sofka.handler.TransactionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/transaction")
public class TransactionController {

    private final TransactionHandler transactionHandler;

    public TransactionController(TransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> findAll() {
        return new ResponseEntity<>(transactionHandler.getTransactions(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> create(@RequestBody TransactionRequestDTO transactionRequestDTO) {

        TransactionResponseDTO transactionResponseDTO = transactionHandler.createTransaction(transactionRequestDTO);
        return new ResponseEntity<>(transactionResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TransactionResponseDTO> update(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        TransactionResponseDTO transactionResponseDTO = transactionHandler.updateTransaction(transactionRequestDTO);
        return new ResponseEntity<>(transactionResponseDTO, HttpStatus.OK);
    }
}
