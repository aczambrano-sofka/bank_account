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

        Customer customer = customerRepository.findById(identification)
                .orElseThrow(() -> new ConflictException("Customer not found"));

        customer.setStatus(false);
        customerRepository.update(customer);
    }

}
