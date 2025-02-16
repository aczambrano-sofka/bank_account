package ec.com.sofka;
import ec.com.sofka.customer.FindAllCustomerUseCase;
import ec.com.sofka.gateway.ICustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
public class FindAllCustomerUseCaseTest {

    @Mock
    private ICustomerRepository customerRepository;

    @InjectMocks
    private FindAllCustomerUseCase findAllCustomerUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_CustomersExist_ReturnsListOfCustomers() {
        Customer customer1 = new Customer("John Doe", "Male", 30, "123456789", "Address", "123-456-7890", 1, "password", true);
        Customer customer2 = new Customer("Jane Smith", "Female", 25, "987654321", "Address2", "098-765-4321", 2, "password2", false);
        List<Customer> customers = Arrays.asList(customer1, customer2);

        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> result = findAllCustomerUseCase.execute();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Smith", result.get(1).getName());
    }

    @Test
    void execute_NoCustomersExist_ReturnsEmptyList() {
        List<Customer> emptyList = Arrays.asList();
        when(customerRepository.findAll()).thenReturn(emptyList);

        List<Customer> result = findAllCustomerUseCase.execute();

        assertNotNull(result);
        assertEquals(0, result.size());
    }
}
