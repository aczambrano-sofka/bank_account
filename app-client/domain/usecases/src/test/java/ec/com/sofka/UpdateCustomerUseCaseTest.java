package ec.com.sofka;

import ec.com.sofka.customer.UpdateCustomerUseCase;
import ec.com.sofka.gateway.ICustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UpdateCustomerUseCaseTest {

    @Mock
    private ICustomerRepository customerRepository;

    @InjectMocks
    private UpdateCustomerUseCase updateCustomerUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_ExistingCustomer_Success() {
        Integer customerId = 1;
        Customer customer = new Customer("John Doe", "Male", 30, "123456789", "Address", "123-456-7890", customerId, "password", true);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerRepository.update(any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Customer updatedCustomer = updateCustomerUseCase.execute(customer);

        assertNotNull(updatedCustomer);
        assertEquals("John Doe", updatedCustomer.getName());
        assertNotEquals("password", updatedCustomer.getPassword());
    }

    @Test
    void execute_NonExistingCustomer_ThrowsConflictException() {
        Integer customerId = 1;
        Customer customer = new Customer("John Doe", "Male", 30, "123456789", "Address", "123-456-7890", customerId, "password", true);
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(ConflictException.class, () -> updateCustomerUseCase.execute(customer));
    }
}
