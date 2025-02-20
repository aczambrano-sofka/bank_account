package ec.com.sofka.customer;

import ec.com.sofka.ConflictException;
import ec.com.sofka.Customer;
import ec.com.sofka.PasswordUtils;
import ec.com.sofka.gateway.ICustomerRepository;

public class CreateCustomerUseCase {

    private final ICustomerRepository customerRepository;

    public CreateCustomerUseCase(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer execute(Customer customer) {

        customerRepository.findByIdentification(customer.getIdentification())
                .ifPresent( customer1 -> {
                    throw new ConflictException("The identification is already registered.");
                });

        customer.setPassword(PasswordUtils.encryptPassword(customer.getPassword()));
        return customerRepository.save(customer);
    }

}
