package ec.com.sofka.config;

import ec.com.sofka.data.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IMysqlCustomerRepository extends JpaRepository <CustomerEntity, Integer> {

    Optional<CustomerEntity> findById(Integer id);
    Optional<CustomerEntity> findByIdentification(String identification);
    List<CustomerEntity> findAll();
}
