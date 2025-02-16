package ec.com.sofka;

import ec.com.sofka.Customer;
import ec.com.sofka.customer.GetCustomerByIdUseCase;
import ec.com.sofka.gateway.ICustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class GetCustomerByIdUseCaseTest {

    @Mock
    private ICustomerRepository customerRepository;

    @InjectMocks
    private GetCustomerByIdUseCase getCustomerByIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_ExistingCustomerId_ReturnsCustomer() {
        Integer customerId = 1;
        Customer customer = new Customer("John Doe", "Male", 30, "123456789", "Address", "123-456-7890", customerId, "password", true);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        Optional<Customer> result = getCustomerByIdUseCase.execute(customerId);

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
        assertEquals(customerId, result.get().getCustomerId());
    }

    @Test
    void execute_NonExistingCustomerId_ReturnsEmptyOptional() {
        Integer customerId = 1;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        Optional<Customer> result = getCustomerByIdUseCase.execute(customerId);

        assertFalse(result.isPresent());
    }

}
