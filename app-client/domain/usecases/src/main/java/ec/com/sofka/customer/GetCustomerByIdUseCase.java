package ec.com.sofka.customer;

import ec.com.sofka.Customer;
import ec.com.sofka.gateway.ICustomerRepository;

import java.util.Optional;

public class GetCustomerByIdUseCase {

    private final ICustomerRepository customerRepository;
    public GetCustomerByIdUseCase(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> execute(Integer id) {
        return customerRepository.findById(id);
    }

}
