package com.example.customerapp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.customerapp.controller.CustomerController;
import com.example.customerapp.customer.Customer;
import com.example.customerapp.customerService.CustomerService;
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

     @Autowired
    private MockMvc mockMvc;

    @MockitoBean // Use @MockBean if on Spring Boot < 3.4
    private CustomerService service;

    //test the Get endpoint
    @Test
   void testGetCustomer() throws Exception {

    // Arrange
    Customer customer = new Customer(5, "Ian", "savings");

    when(service.getCustomer(5)).thenReturn(customer);

    // Your Act and Assert sections go here
    mockMvc.perform(get("/customer/5")).andExpect((status().isOk())).andExpect(jsonPath("$.name").value("Ian"));

    //verify service was called
    verify(service).getCustomer(5);

  }

  //test
  @Test
  void testPostCustomer() throws Exception {
    Customer savedCustomer = new Customer(5, "Ian", "savings");
    
    // Accept ANY Customer object created by Spring's JSON parser
    when(service.createCustomer(any(Customer.class))).thenReturn(savedCustomer);

    // Act & Assert
    mockMvc.perform(post("/customer")
           .contentType(MediaType.APPLICATION_JSON)
           .content("{\"name\":\"Ian\",\"accountType\":\"savings\"}"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.name").value("Ian"));

    verify(service).createCustomer(any(Customer.class));
  }
 
  //tests for deleting customer
  @Test
  void testDeleteCustomer()throws Exception{
    // Act & Assert
    mockMvc.perform(delete("/customer/5"))
           .andExpect(status().isNoContent()); // Use isOk() if your controller returns 200

    // Verify the controller actually called the service with ID 5
    verify(service, times(1)).deleteCustomer(5);

  }

  //test blank field(s)
  @Test 
  void testBlankCustomerField() throws Exception{
    // Act & Assert
    mockMvc.perform(post("/customer")
           .contentType(MediaType.APPLICATION_JSON)
           .content("{\"name\":\"Ian\",\"accountType\":\" \"}"))
           .andExpect(status().isBadRequest());

    verify(service, never()).createCustomer(any(Customer.class));
  }
}