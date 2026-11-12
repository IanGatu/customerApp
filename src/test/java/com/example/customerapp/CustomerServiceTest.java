package com.example.customerapp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.customerapp.customer.Customer;
import com.example.customerapp.customerService.CustomerService;
import com.example.customerapp.customernotfoundexception.CustomerNotFoundException;
import com.example.customerapp.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerService service;

    //TESTS FOR GETCUSTOMER

    @Test
    //testing if the getcustomer logic works correctly
    void testGetCustomer() {

        // Arrange
        Customer customer =
            new Customer(5, "Ian", "savings");

        when(repository.findById(5))
            .thenReturn(Optional.of(customer));

        // Act
        Customer result = service.getCustomer(5);

        // Assert
        assertEquals("Ian", result.getName());
    }
    @Test 
    //testing how the customernotfound is handled
    void testGetCustomerNotFOund(){

        //Arrange

        when(repository.findById(5)).thenReturn(Optional.empty());

        // Act & Assert
      assertThrows(CustomerNotFoundException.class, () -> service.getCustomer(5));

      //assertEquals("Customer with ID 5 not found", e.getMessage());
    //assertEquals("Customer with ID 5 not found", exception.getMessage());
    }

    @Test 
    //testing invalid id
    void testGetCustomerInvalidId(){
        //act and assert
        assertThrows(IllegalArgumentException.class,() ->  service.getCustomer(0));
        //verify that the repository was never called
        verify(repository, never()).findById(0);

    }

    //Tests for CreateCustomer
    @Test
void testCreateCustomer() {

    // Arrange
    Customer inputCustomer = new Customer();
    inputCustomer.setName("Doe");
    inputCustomer.setAccountType("savings");

    // Simulate PostgreSQL generating ID 10
    Customer savedCustomer = new Customer(10, "Doe", "savings");

    when(repository.save(inputCustomer)).thenReturn(savedCustomer);

    // Act
    Customer results = service.createCustomer(inputCustomer);

    // Assert
    assertNotNull(results);
    assertEquals(10, results.getId());
    assertEquals("Doe", results.getName());
    assertEquals("savings", results.getAccountType());

    // Verify repository interaction
    verify(repository).save(inputCustomer);
}


//Tests for updateCustomer
@Test 
void testUpdateCustomer(){
    //arrange
    Customer customer =
            new Customer(5, "Ian", "savings");

        when(repository.findById(5))
            .thenReturn(Optional.of(customer));
            
     //act
    Customer result = service.updateCustomer(5,"grace");

    //Assert
    assertEquals("grace", result.getName());

}
    
}