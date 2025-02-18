package ec.com.sofka;

import ec.com.sofka.data.CustomerEntity;
import ec.com.sofka.database.IMysqlCustomerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class CustomerRepositoryTest {

    @Autowired
    private IMysqlCustomerRepository mysqlCustomerRepository;

    @BeforeEach
    public void setUp() {

        mysqlCustomerRepository.deleteAll();

        CustomerEntity customer = new CustomerEntity();
        customer.setIdentification("123456789");
        customer.setName("Juan Pérez");
        customer.setPassword("password");
        customer.setStatus(true);
        mysqlCustomerRepository.save(customer);
    }

    @Test
    void whenFindByIdentification_thenReturnCustomer() {
        Optional<CustomerEntity> customerOptional = mysqlCustomerRepository.findByIdentification("123456789");

        assertTrue(customerOptional.isPresent());

        CustomerEntity customer = customerOptional.get();

        assertNotNull(customer);
        assertEquals("123456789", customer.getIdentification());
        assertEquals("Juan Pérez", customer.getName());
        assertEquals("password", customer.getPassword());
        assertTrue(customer.getStatus());
    }

    @Test
    void whenSaveNewCustomer_thenCustomerIsCreated() {
        CustomerEntity customer = new CustomerEntity("Alice", "Female", 28, "555555555", "Another Address", "555-555-5555", "securePassword", true);

        CustomerEntity savedCustomer = mysqlCustomerRepository.save(customer);

        assertNotNull(savedCustomer.getCustomerId()); // Check if ID was generated
        Optional<CustomerEntity> retrievedCustomer = mysqlCustomerRepository.findById(savedCustomer.getCustomerId());
        assertTrue(retrievedCustomer.isPresent()); // Check if customer exists
        assertEquals("Alice", retrievedCustomer.get().getName()); // Check data
        assertEquals("555555555", retrievedCustomer.get().getIdentification());
    }

    @Test
    void whenUpdateExistingCustomer_thenCustomerIsUpdated() {
        CustomerEntity customer = new CustomerEntity("Bob", "Male", 40, "777777777", "Some Address", "777-777-7777", "initialPassword", true);
        CustomerEntity savedCustomer = mysqlCustomerRepository.save(customer);

        savedCustomer.setName("Robert");
        savedCustomer.setStatus(false);
        CustomerEntity updatedCustomer = mysqlCustomerRepository.save(savedCustomer);

        Optional<CustomerEntity> retrievedCustomer = mysqlCustomerRepository.findById(savedCustomer.getCustomerId());
        assertTrue(retrievedCustomer.isPresent());
        assertEquals("Robert", retrievedCustomer.get().getName());
        assertFalse(retrievedCustomer.get().getStatus());
    }

}
