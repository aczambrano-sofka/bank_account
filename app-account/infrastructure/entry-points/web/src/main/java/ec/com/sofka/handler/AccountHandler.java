package ec.com.sofka.handler;

import ec.com.sofka.Account;
import ec.com.sofka.account.CreateAccountUseCase;
import ec.com.sofka.account.DeleteAccountUseCase;
import ec.com.sofka.account.FindAllAccountUseCase;
import ec.com.sofka.account.UpdateAccountUseCase;
import ec.com.sofka.data.AccountRequestDTO;
import ec.com.sofka.data.AccountResponseDTO;
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


    public AccountHandler(CreateAccountUseCase createAccountUseCase, UpdateAccountUseCase updateAccountUseCase, DeleteAccountUseCase deleteAccountUseCase, FindAllAccountUseCase findAllAccountUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.updateAccountUseCase = updateAccountUseCase;
        this.deleteAccountUseCase = deleteAccountUseCase;
        this.findAllAccountUseCase = findAllAccountUseCase;
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

}
