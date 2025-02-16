package ec.com.sofka;

import ec.com.sofka.customer.DeleteCustomerUseCase;
import ec.com.sofka.gateway.ICustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class DeleteCustomerUseCaseTest {

    @Mock
    private ICustomerRepository customerRepository;

    @InjectMocks
    private DeleteCustomerUseCase deleteCustomerUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_ExistingCustomer_Success() {
        Integer customerId = 123;
        Customer customer = new Customer("John Doe", "Male", 30, "123456789", "Address", "123-456-7890", customerId, "password", true);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        deleteCustomerUseCase.execute(customerId);

        verify(customerRepository, times(1)).findById(customerId);
        verify(customerRepository, times(1)).update(customer);
        assertFalse(customer.getStatus());
    }


}
