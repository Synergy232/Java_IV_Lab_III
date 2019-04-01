package edu.cscc.java4.rest;

import edu.cscc.java4.rest.data.CustomerRepository;
import edu.cscc.java4.rest.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CustomerControllerTests {

  private static final String RESOURCE_URI = "/api/customers";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CustomerRepository customerRepository;


  @Test
  public void isPersistenceDesigedCorrectly_Test () throws ClassNotFoundException {
    assertTrue(Class.forName("edu.cscc.java4.rest.data.CustomerRepository").isInterface());
  }

  /* ========== Uncomment one test at a time adding just enough code to get it to pass ========

  @Test
  public void getAllCustomersWorksWithEmptyList_Test () throws Exception {
    // No customers:
    when(customerRepository.findAll()).thenReturn(getMockCustomers());
    this.mockMvc.perform(get(RESOURCE_URI)).andExpect(status().isOk())
      .andExpect(jsonPath("$.customers.length()").value(0));
  }


  @Test
  public void getAllCustomersWorks_Test () throws Exception {
    // One customer:
    when(customerRepository.findAll()).thenReturn(getMockCustomers(
      new Customer(0L, "f1", "l1", "e1")));
    this.mockMvc.perform(get(RESOURCE_URI)).andExpect(status().isOk())
      .andExpect(jsonPath("$.customers.length()").value(1))
      .andExpect(jsonPath("$.customers[0].lastName").value("l1"))
      .andExpect(jsonPath("$.customers[0].firstName").value("f1"));

    // Two customers:
    when(customerRepository.findAll()).thenReturn(getMockCustomers(
      new Customer(0L, "f1", "l1", "e1"),
      new Customer(1L, "f2", "l2", "e2")));
    this.mockMvc.perform(get(RESOURCE_URI)).andExpect(status().isOk())
      .andExpect(jsonPath("$.customers.length()").value(2))
      .andExpect(jsonPath("$.customers[0].lastName").value("l1"))
      .andExpect(jsonPath("$.customers[0].firstName").value("f1"))
      .andExpect(jsonPath("$.customers[1].lastName").value("l2"))
      .andExpect(jsonPath("$.customers[1].firstName").value("f2"));
  }

  @Test
  public void getValidCustomerByIdWorks_Test () throws Exception {
    Customer testCustomer = new Customer(1L, "first", "last", "foo@bar.com");
    when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));
    this.mockMvc.perform(get(RESOURCE_URI + "/1")).andExpect(status().isOk())
      .andExpect(jsonPath("$.lastName").value(testCustomer.getLastName()))
      .andExpect(jsonPath("$.firstName").value(testCustomer.getFirstName()));
  }


  @Test
  public void getInvalidCustomerByIdReturnsNotFound_Test () throws Exception {
    when(customerRepository.findById(1L)).thenReturn(Optional.empty());
    this.mockMvc.perform(get(RESOURCE_URI + "/1")).andExpect(status().isNotFound());
  }


  @Test
  public void deleteValidCustomerReturnsNoContent_Test () throws Exception {
    Customer testCustomer = new Customer(1L, "first", "last", "foo@bar.com");
    when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));
    this.mockMvc.perform(delete(RESOURCE_URI + "/1")).andExpect(status().isNoContent());
    verify(customerRepository, times(1)).delete(any(Customer.class));
  }


  @Test
  public void deleteInvalidCustomerReturnsNotFound_Test () throws Exception {
    when(customerRepository.findById(1L)).thenReturn(Optional.empty());
    this.mockMvc.perform(delete(RESOURCE_URI + "/1")).andExpect(status().isNotFound());
    verify(customerRepository, never()).delete(any(Customer.class));
  }


  @Test
  public void postCreatesNewCustomer_Test () throws Exception {
    Customer testCustomer = new Customer(1L, "first", "last", null);
    when(customerRepository.save(any(Customer.class))).thenReturn(testCustomer);

    MvcResult result = this.mockMvc.perform(post(RESOURCE_URI).contentType(MediaType.APPLICATION_JSON_UTF8)
      .content("{\"firstName\": \"first\", \"lastName\": \"last\", \"emailAddress\": \"foo@bar.com\"}"))
      .andExpect(status().isCreated()).andReturn();
    ;
    verify(customerRepository, times(1)).save(any(Customer.class));
    MockHttpServletResponse mockResponse = result.getResponse();
    assertEquals("http://localhost/api/customers/1", mockResponse.getHeader("Location"));
  }


  @Test
  public void putExistingCustomerCallsSave_Test () throws Exception {
    when(customerRepository.existsById(1L)).thenReturn(true);
    this.mockMvc.perform(put(RESOURCE_URI + "/1").contentType(MediaType.APPLICATION_JSON_UTF8)
      .content("{\"firstName\": \"first\", \"lastName\": \"last\", \"emailAddress\": \"foo@bar.com\"}"))
      .andExpect(status().isNoContent());
    verify(customerRepository, times(1)).save(any(Customer.class));
  }


  @Test
  public void putNonexistentCustomerReturnsNotFound_Test () throws Exception {
    when(customerRepository.existsById(1L)).thenReturn(false);
    this.mockMvc.perform(put(RESOURCE_URI + "/1").contentType(MediaType.APPLICATION_JSON_UTF8)
      .content("{\"firstName\": \"first\", \"lastName\": \"last\", \"emailAddress\": \"foo@bar.com\"}"))
      .andExpect(status().isNotFound());
    verify(customerRepository, never()).save(any(Customer.class));
  }

   */

  private Collection<Customer> getMockCustomers (Customer... customerArgs) {
    HashMap<Long, Customer> customers = new HashMap<>();

    for (Customer customer : customerArgs) {
      customers.put(customer.getId(), customer);
    }

    return customers.values();
  }

}
