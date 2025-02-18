package ec.com.sofka.gateway;

import ec.com.sofka.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository {
    Customer save(Customer customer);
    Customer update(Customer customer);
    List<Customer> findAll();
    Optional<Customer> findById(Integer id);
    Optional<Customer> findByIdentification(String identification);
}
