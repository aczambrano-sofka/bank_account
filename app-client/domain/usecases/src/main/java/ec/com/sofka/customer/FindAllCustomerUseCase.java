package ec.com.sofka.customer;

import ec.com.sofka.Customer;
import ec.com.sofka.gateway.ICustomerRepository;

import java.util.List;

public class FindAllCustomerUseCase {

    private final ICustomerRepository customerRepository;

    public FindAllCustomerUseCase(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> execute() {
        return customerRepository.findAll();
    }
}
