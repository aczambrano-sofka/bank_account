package ec.com.sofka.controller;

import ec.com.sofka.data.CustomerRequestDTO;
import ec.com.sofka.data.CustomerResponseDTO;
import ec.com.sofka.handler.CustomerHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerHandler customerHandler;

    public CustomerController(CustomerHandler customerHandler) {
        this.customerHandler = customerHandler;
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> findAll() {
        return new ResponseEntity<>(customerHandler.getCustomers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        CustomerResponseDTO response = customerHandler.createCustomer(customerRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        CustomerResponseDTO response = customerHandler.updateCustomer(customerRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{identification}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String identification) {
        customerHandler.deleteCustomer(new CustomerRequestDTO(identification));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
