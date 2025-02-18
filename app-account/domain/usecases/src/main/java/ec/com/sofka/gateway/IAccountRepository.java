package ec.com.sofka.gateway;

import ec.com.sofka.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountRepository {
    List<Account> findAll();
    Optional<Account> findById(Integer id);
    Optional<Account>  findByAccountNumber(String accountNumber);
    Account save(Account account);
    Account update(Account account);
    Optional<Account> findByCustomerId(Integer customerId);
}
