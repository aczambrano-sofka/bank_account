package ec.com.sofka.customer;

import ec.com.sofka.ConflictException;
import ec.com.sofka.Customer;
import ec.com.sofka.gateway.ICustomerRepository;

import java.util.Optional;

public class DeleteCustomerUseCase {

    private final ICustomerRepository customerRepository;

    public DeleteCustomerUseCase(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void execute(Integer identification) {
        Optional<Customer> existingCustomerOpt = customerRepository.findById(identification);

        if (existingCustomerOpt.isEmpty()) {
            throw new ConflictException("The customer with the given identification does not exist.");
        }

        Customer existingCustomer = existingCustomerOpt.get();
        existingCustomer.setStatus(false);

        customerRepository.update(existingCustomer);
    }

}
