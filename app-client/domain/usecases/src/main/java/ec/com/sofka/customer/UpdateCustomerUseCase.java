package ec.com.sofka.customer;

import ec.com.sofka.ConflictException;
import ec.com.sofka.Customer;
import ec.com.sofka.gateway.ICustomerRepository;

import java.util.Optional;

public class UpdateCustomerUseCase {

    private final ICustomerRepository customerRepository;

    public UpdateCustomerUseCase(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer execute(Customer customer){
        Optional<Customer> existingCustomerOpt = customerRepository.findById(customer.getCustomerId());

        if (existingCustomerOpt.isEmpty()){
            throw new ConflictException("The customer with the given identification does not exist.");
        }

        return customerRepository.update(customer);
    }
}
