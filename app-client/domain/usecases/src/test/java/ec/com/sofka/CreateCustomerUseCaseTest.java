package ec.com.sofka;

import ec.com.sofka.customer.CreateCustomerUseCase;
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

public class CreateCustomerUseCaseTest {

    @Mock
    private ICustomerRepository customerRepository;

    @InjectMocks
    private CreateCustomerUseCase createCustomerUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_NewCustomer_Success() {
        Customer customer = new Customer("John Doe", "Male", 30, "123456789", "Address", "123-456-7890", null, "password", true); // Use the correct constructor
        when(customerRepository.findByIdentification(customer.getIdentification())).thenReturn(Optional.empty());
        when(customerRepository.save(any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0)); // Return the saved customer

        Customer savedCustomer = createCustomerUseCase.execute(customer);

        assertNotNull(savedCustomer);
        assertEquals("John Doe", savedCustomer.getName());
        assertNotEquals("password", savedCustomer.getPassword()); // Check that password was encrypted
    }

    @Test
    void execute_ExistingIdentification_ThrowsConflictException() {
        Customer customer = new Customer("John Doe", "Male", 30, "123456789", "Address", "123-456-7890", null, "password", true); // Use the correct constructor
        when(customerRepository.findByIdentification(customer.getIdentification())).thenReturn(Optional.of(customer));

        assertThrows(ConflictException.class, () -> createCustomerUseCase.execute(customer));
    }
}
