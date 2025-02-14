package ec.com.sofka.database;

import ec.com.sofka.data.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IMySqlAccountRepository extends JpaRepository<AccountEntity, Integer> {

    Optional<AccountEntity> findById(Integer id);
    Optional<AccountEntity> findByAccountNumber(String accountNumber);
    List<AccountEntity> findAll();

}
