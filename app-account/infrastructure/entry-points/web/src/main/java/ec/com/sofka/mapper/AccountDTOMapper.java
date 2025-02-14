package ec.com.sofka.mapper;

import ec.com.sofka.Account;
import ec.com.sofka.data.AccountRequestDTO;
import ec.com.sofka.data.AccountResponseDTO;

public class AccountDTOMapper {


    public static AccountResponseDTO toAccountResponseDTO(Account account) {
        return new AccountResponseDTO(
                account.getAccountId(),
                account.getAccountNumber(),
                account.getAccountType(),
                account.getInitialBalance(),
                account.getStatus(),
                account.getCustomerId()
        );
    }

    public static Account toAccount(AccountRequestDTO accountRequestDTO) {
        return new Account(
                accountRequestDTO.getAccountId(),
                accountRequestDTO.getAccountNumber(),
                accountRequestDTO.getAccountType(),
                accountRequestDTO.getInitialBalance(),
                accountRequestDTO.getStatus(),
                accountRequestDTO.getCustomerId()
        );
    }

}
