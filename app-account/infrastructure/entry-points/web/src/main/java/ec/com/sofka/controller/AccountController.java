package ec.com.sofka.controller;

import ec.com.sofka.data.AccountRequestDTO;
import ec.com.sofka.data.AccountResponseDTO;
import ec.com.sofka.data.AccountStatementResponseDTO;
import ec.com.sofka.handler.AccountHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    private final AccountHandler accountHandler;

    public AccountController(AccountHandler accountHandler) {
        this.accountHandler = accountHandler;
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> findAll() {
        return new ResponseEntity<>(accountHandler.getAccounts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountRequestDTO accountRequestDTO) {
        AccountResponseDTO response = accountHandler.createAccount(accountRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<AccountResponseDTO> updateAccount(@RequestBody AccountRequestDTO accountRequestDTO) {
        AccountResponseDTO response = accountHandler.updateAccount(accountRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Integer accountId) {
        accountHandler.deleteAccount(accountId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/reports")
    public ResponseEntity<List<AccountStatementResponseDTO>> getReports(
            @RequestParam("date") String dateRange,
            @RequestParam("customerId") Integer customerIdentification
    ) {
        return ResponseEntity.ok(accountHandler.getAccountStatements(dateRange, customerIdentification));
    }


}
