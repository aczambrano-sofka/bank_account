package ec.com.sofka.adapter;

import ec.com.sofka.Account;
import ec.com.sofka.data.AccountEntity;
import ec.com.sofka.database.IMySqlAccountRepository;
import ec.com.sofka.gateway.IAccountRepository;
import ec.com.sofka.mapper.AccountMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AccountAdapter implements IAccountRepository {

    private final IMySqlAccountRepository accountRepository;

    public AccountAdapter(IMySqlAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public List<Account> findAll() {
        List<AccountEntity> accountEntities = accountRepository.findAll();
        return accountEntities.stream()
                .map(AccountMapper::entityToAccount)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Account> findById(Integer id) {
        Optional<AccountEntity> accountEntity = accountRepository.findById(id);
        return accountEntity.map(AccountMapper::entityToAccount);
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        Optional<AccountEntity> accountEntity = accountRepository.findByAccountNumber(accountNumber);
        return accountEntity.map(AccountMapper::entityToAccount);
    }

    @Override
    public Account save(Account account) {
        AccountEntity accountEntity = AccountMapper.accountToEntity(account);
        AccountEntity savedEntity = accountRepository.save(accountEntity);
        return AccountMapper.entityToAccount(savedEntity);
    }

    @Override
    public Account update(Account account) {
        Optional<AccountEntity> existingEntity = accountRepository.findById(account.getAccountId());

        if (existingEntity.isPresent()) {
            AccountEntity updatedEntity = existingEntity.get();
            updatedEntity.setAccountNumber(account.getAccountNumber());
            updatedEntity.setAccountType(account.getAccountType());
            updatedEntity.setInitialBalance(account.getInitialBalance());
            updatedEntity.setStatus(account.getStatus());
            updatedEntity.setCustomerId(account.getCustomerId());
            accountRepository.save(updatedEntity);
            return AccountMapper.entityToAccount(updatedEntity);
        } else {
            throw new RuntimeException("Account not found");
        }
    }

    @Override
    public Optional<Account> findByCustomerId(Integer customerId) {
        Optional<AccountEntity> accountEntity = accountRepository.findByCustomerId(customerId);
        return accountEntity.map(AccountMapper::entityToAccount);
    }
}
