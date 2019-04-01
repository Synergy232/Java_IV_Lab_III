package edu.cscc.java4.rest;

import edu.cscc.java4.rest.data.CustomerRepository;
import edu.cscc.java4.rest.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collection;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CustomerControllerValidationTests {

  private static final String RESOURCE_URI = "/api/customers";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CustomerRepository customerRepository;

  /*
   *   OPTIONAL  -- Bonus learning that is completely optional but recommended to learn Java Bean validation for REST
   *   OPTIONAL  -- Bonus learning that is completely optional but recommended to learn Java Bean validation for REST
   *   OPTIONAL  -- Bonus learning that is completely optional but recommended to learn Java Bean validation for REST
   *   OPTIONAL  -- Bonus learning that is completely optional but recommended to learn Java Bean validation for REST
   *   OPTIONAL  -- Bonus learning that is completely optional but recommended to learn Java Bean validation for REST
   *   OPTIONAL  -- Bonus learning that is completely optional but recommended to learn Java Bean validation for REST
   *   OPTIONAL  -- Bonus learning that is completely optional but recommended to learn Java Bean validation for REST
   *   OPTIONAL  -- Bonus learning that is completely optional but recommended to learn Java Bean validation for REST
   *   OPTIONAL  -- Bonus learning that is completely optional but recommended to learn Java Bean validation for REST
   *   OPTIONAL  -- Bonus learning that is completely optional but recommended to learn Java Bean validation for REST
   *   OPTIONAL  -- Bonus learning that is completely optional but recommended to learn Java Bean validation for REST
   */


  /* ========== Uncomment one test at a time adding just enough code to get it to pass ========


  @Test
  public void createShouldValidateCustomer() throws Exception {
    this.mockMvc.perform(post(RESOURCE_URI).content("{}")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$.fieldErrors", hasSize(2)))
      .andExpect(jsonPath("$.fieldErrors[?(@.field =~ /firstName/i)].code").value("NotBlank"))
      .andExpect(jsonPath("$.fieldErrors[?(@.field =~ /lastName/i)].code").value("NotBlank"));
    verify(customerRepository, never()).save(any(Customer.class));
  }


  @Test
  public void putShouldValidateCustomer() throws Exception {
    mockMvc.perform(put(RESOURCE_URI_BY_ID, 1L).content("{\"firstName\": \"   \"}")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$.fieldErrors", hasSize(2)))
      .andExpect(jsonPath("$.fieldErrors[?(@.field =~ /firstName/i)].code").value("NotBlank"))
      .andExpect(jsonPath("$.fieldErrors[?(@.field =~ /lastName/i)].code").value("NotBlank"));
    verify(customerRepository, never()).save(any(Customer.class));
  }

  */

}
