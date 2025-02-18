package ec.com.sofka.handler;

import ec.com.sofka.Account;
import ec.com.sofka.account.*;
import ec.com.sofka.data.AccountRequestDTO;
import ec.com.sofka.data.AccountResponseDTO;
import ec.com.sofka.data.AccountStatementResponseDTO;
import ec.com.sofka.mapper.AccountDTOMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountHandler {

    private final CreateAccountUseCase createAccountUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;
    private final FindAllAccountUseCase findAllAccountUseCase;
    private final GetAccountStatementUseCase getAccountStatementUseCase;

    public AccountHandler(CreateAccountUseCase createAccountUseCase, UpdateAccountUseCase updateAccountUseCase, DeleteAccountUseCase deleteAccountUseCase, FindAllAccountUseCase findAllAccountUseCase, GetAccountStatementUseCase getAccountStatementUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.updateAccountUseCase = updateAccountUseCase;
        this.deleteAccountUseCase = deleteAccountUseCase;
        this.findAllAccountUseCase = findAllAccountUseCase;
        this.getAccountStatementUseCase = getAccountStatementUseCase;
    }


    public List<AccountResponseDTO> getAccounts() {
        return findAllAccountUseCase.execute().stream()
                .map(AccountDTOMapper::toAccountResponseDTO)
                .collect(Collectors.toUnmodifiableList());
    }

    public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO) {
        Account account = AccountDTOMapper.toAccount(accountRequestDTO);
        return AccountDTOMapper.toAccountResponseDTO(createAccountUseCase.execute(account));
    }

    public AccountResponseDTO updateAccount(AccountRequestDTO accountRequestDTO) {
        Account account = AccountDTOMapper.toAccount(accountRequestDTO);
        return AccountDTOMapper.toAccountResponseDTO(updateAccountUseCase.execute(account));
    }

    public void deleteAccount(Integer accountId) {
        deleteAccountUseCase.execute(accountId);
    }

    public List<AccountStatementResponseDTO> getAccountStatements(String dateRange, Integer customerIdentification){
        return getAccountStatementUseCase.execute(dateRange,customerIdentification)
                .stream()
                .map( data ->
                        new  AccountStatementResponseDTO(
                                data.getAccountNumber(),
                                data.getAccountInitialBalance(),
                                data.getAccountAvailableBalance(),
                                data.getAccountType(),
                                data.getCustomerName(),
                                data.getStatus(),
                                data.getMovementAmount(),
                                data.getDate()
                        )
                )
                .collect(Collectors.toList());
    }

}
