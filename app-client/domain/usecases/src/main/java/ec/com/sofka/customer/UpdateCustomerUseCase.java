package ec.com.sofka.customer;

import ec.com.sofka.Customer;
import ec.com.sofka.EntityNotFoundException;
import ec.com.sofka.PasswordUtils;
import ec.com.sofka.gateway.ICustomerRepository;

public class UpdateCustomerUseCase {

    private final ICustomerRepository customerRepository;

    public UpdateCustomerUseCase(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer execute(Customer customer){

        customerRepository.findById(customer.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        customer.setPassword(PasswordUtils.encryptPassword(customer.getPassword()));


        return customerRepository.update(customer);
    }
}
