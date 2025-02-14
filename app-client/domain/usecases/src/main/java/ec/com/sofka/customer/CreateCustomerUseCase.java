package ec.com.sofka.customer;

import ec.com.sofka.ConflictException;
import ec.com.sofka.Customer;
import ec.com.sofka.PasswordUtils;
import ec.com.sofka.gateway.ICustomerRepository;

import java.util.Optional;

public class CreateCustomerUseCase {

    private final ICustomerRepository customerRepository;

    public CreateCustomerUseCase(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer execute(Customer customer) {
        Optional<Customer> existingCustomerOpt = customerRepository.findByIdentification(customer.getIdentification());

        if (existingCustomerOpt.isPresent()) {
            throw new ConflictException("The identification is already registered.");
        }
        customer.setPassword(PasswordUtils.encryptPassword(customer.getPassword()));
        return customerRepository.save(customer);
    }

}
