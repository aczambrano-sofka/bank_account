package ec.com.sofka.customer;

import ec.com.sofka.ConflictException;
import ec.com.sofka.Customer;
import ec.com.sofka.PasswordUtils;
import ec.com.sofka.gateway.ICustomerRepository;

import java.util.Optional;

public class UpdateCustomerUseCase {

    private final ICustomerRepository customerRepository;

    public UpdateCustomerUseCase(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer execute(Customer customer){

        customerRepository.findById(customer.getCustomerId())
                .orElseThrow(() -> new ConflictException("Customer not found"));

        customer.setPassword(PasswordUtils.encryptPassword(customer.getPassword()));


        return customerRepository.update(customer);
    }
}
