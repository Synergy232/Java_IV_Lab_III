package edu.cscc.java4.rest.ui;

import edu.cscc.java4.rest.data.CustomerRepository;
import edu.cscc.java4.rest.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;

/**
 * @author Jeff Anderson
 * @since 2019-04-20
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UiCustomerController.class)
public class UiCustomerContollerTests {

  private static final String RESOURCE_URI = "/customers";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CustomerRepository customerRepository;

  @Test
  public void smoke_Test () throws Exception {
  }

  @Test
  public void getAllCustomersWorksWithEmptyList_Test () throws Exception {
    // No customers:
    Collection<Customer> noCustomers = getMockCustomers();
    Mockito.when(customerRepository.findAll()).thenReturn(noCustomers);
    this.mockMvc.perform(MockMvcRequestBuilders.get(RESOURCE_URI)).andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.model().attribute("customers", equalTo(noCustomers)))
      .andExpect(MockMvcResultMatchers.view().name("customers"));
  }

  @Test
  public void getAllCustomersWorksWithPopulatedList_Test () throws Exception {
    Collection<Customer> customers = getMockCustomers(
      new Customer(0L, "f1", "l1", "e1"),
      new Customer(1L, "f2", "l2", "e2"));
    Mockito.when(customerRepository.findAll()).thenReturn(customers);
    this.mockMvc.perform(MockMvcRequestBuilders.get(RESOURCE_URI)).andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.model().attribute("customers", equalTo(customers)))
      .andExpect(MockMvcResultMatchers.view().name("customers"));
  }

  @Test
  public void getNewCustomerWorks_Test () throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get(RESOURCE_URI + "/new"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.model().attributeExists("customer"))
      .andExpect(MockMvcResultMatchers.view().name("customer"));
  }

  @Test
  public void postValidCreatesNewCustomer_Test () throws Exception {

    Customer testCustomer = new Customer(1L, "first", "last", "foo@bar.com");
    Mockito.when(customerRepository.save(ArgumentMatchers.any(Customer.class))).thenReturn(testCustomer);

    this.mockMvc.perform(MockMvcRequestBuilders.post(RESOURCE_URI).contentType(MediaType.APPLICATION_JSON_UTF8)
      .param("firstName", "firstName")
      .param("lastName", "lastName")
      .param("emailAddress", "foo@bar.com"))
      .andExpect(MockMvcResultMatchers.status().isFound())
      .andExpect(flash().attributeCount(1))
      .andExpect(flash().attribute("message", "Customer 'firstName lastName' saved"))
      .andExpect(MockMvcResultMatchers.view().name("redirect:/customers"));
    Mockito.verify(customerRepository, VerificationModeFactory.times(1)).save(ArgumentMatchers.any(Customer.class));
  }

  @Test
  public void postInvalidDoesNotCreateNewCustomer_Test () throws Exception {

    this.mockMvc.perform(MockMvcRequestBuilders.post(RESOURCE_URI).contentType(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(flash().attributeCount(0))
      .andExpect(MockMvcResultMatchers.view().name("customer"))
      .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("customer","firstName"))
      .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("customer","lastName"))
      .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("customer","emailAddress"));
    Mockito.verify(customerRepository, Mockito.never()).save(ArgumentMatchers.any(Customer.class));
  }

  @Test
  public void getValidCustomerByIdWorks_Test () throws Exception {
    Customer testCustomer = new Customer(1L, "first", "last", "foo@bar.com");
    Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));
    this.mockMvc.perform(MockMvcRequestBuilders.get(RESOURCE_URI + "/1"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.model().attribute("customer", equalTo(testCustomer)))
      .andExpect(MockMvcResultMatchers.view().name("customer"));
  }

  @Test
  public void getInvalidCustomerByIdReturnsNotFound_Test () throws Exception {
    Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.empty());
    this.mockMvc.perform(MockMvcRequestBuilders.get(RESOURCE_URI + "/1"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.view().name("error/404"));
  }

  @Test
  public void deleteValidCustomerWorksProperly_Test () throws Exception {
    Customer testCustomer = new Customer(1L, "first", "last", "foo@bar.com");
    Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));
    this.mockMvc.perform(MockMvcRequestBuilders.get(RESOURCE_URI + "/delete/1"))
      .andExpect(MockMvcResultMatchers.status().isFound())
      .andExpect(flash().attributeCount(1))
      .andExpect(flash().attribute("message", "Customer 1 deleted"))
      .andExpect(MockMvcResultMatchers.view().name("redirect:/customers"));
    Mockito.verify(customerRepository, VerificationModeFactory.times(1)).delete(ArgumentMatchers.any(Customer.class));
  }

  @Test
  public void deleteInvalidCustomerReturnsNotFound_Test () throws Exception {
    Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.empty());
    this.mockMvc.perform(MockMvcRequestBuilders.get(RESOURCE_URI + "/delete/1"))
      .andExpect(MockMvcResultMatchers.status().isFound())
      .andExpect(flash().attributeCount(2))
      .andExpect(flash().attribute("error", "True"))
      .andExpect(flash().attribute("message", "Customer 1 not found"))
      .andExpect(MockMvcResultMatchers.view().name("redirect:/customers"));
    Mockito.verify(customerRepository, Mockito.never()).delete(ArgumentMatchers.any(Customer.class));
  }


  private Collection<Customer> getMockCustomers (Customer... customerArgs) {
    HashMap<Long, Customer> customers = new HashMap<>();

    for (Customer customer : customerArgs) {
      customers.put(customer.getId(), customer);
    }

    return customers.values();
  }


}
