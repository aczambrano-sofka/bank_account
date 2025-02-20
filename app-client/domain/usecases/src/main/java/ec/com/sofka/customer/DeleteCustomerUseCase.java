package ec.com.sofka.customer;

import ec.com.sofka.Customer;
import ec.com.sofka.EntityNotFoundException;
import ec.com.sofka.gateway.ICustomerRepository;

public class DeleteCustomerUseCase {

    private final ICustomerRepository customerRepository;

    public DeleteCustomerUseCase(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void execute(Integer identification) {

        Customer customer = customerRepository.findById(identification)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        customer.setStatus(false);
        customerRepository.update(customer);
    }

}
