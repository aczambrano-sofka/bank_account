package ec.com.sofka.handler;

import ec.com.sofka.Customer;
import ec.com.sofka.customer.CreateCustomerUseCase;
import ec.com.sofka.customer.DeleteCustomerUseCase;
import ec.com.sofka.customer.FindAllCustomerUseCase;
import ec.com.sofka.customer.UpdateCustomerUseCase;
import ec.com.sofka.data.CustomerRequestDTO;
import ec.com.sofka.data.CustomerResponseDTO;
import ec.com.sofka.mapper.CustomerDTOMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerHandler {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final FindAllCustomerUseCase findAllCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;


    public CustomerHandler(CreateCustomerUseCase createCustomerUseCase, DeleteCustomerUseCase deleteCustomerUseCase, FindAllCustomerUseCase findAllCustomerUseCase, UpdateCustomerUseCase updateCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.deleteCustomerUseCase = deleteCustomerUseCase;
        this.findAllCustomerUseCase = findAllCustomerUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
    }

    public List<CustomerResponseDTO> getCustomers() {
        return findAllCustomerUseCase.execute().stream()
                .map(CustomerDTOMapper::mapToResponse)
                .collect(Collectors.toUnmodifiableList());
    }

    public CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = CustomerDTOMapper.mapToEntity(customerRequestDTO);
        return CustomerDTOMapper.mapToResponse(createCustomerUseCase.execute(customer));
    }

    public CustomerResponseDTO updateCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = CustomerDTOMapper.mapToEntity(customerRequestDTO);
        return CustomerDTOMapper.mapToResponse(updateCustomerUseCase.execute(customer));
    }

    public CustomerResponseDTO deleteCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = CustomerDTOMapper.mapToEntity(customerRequestDTO);
        deleteCustomerUseCase.execute(customer.getIdentification());
        return new CustomerResponseDTO();
    }

}
















