package edu.cscc.java4.rest;

import edu.cscc.java4.rest.domain.Customer;
import org.junit.Test;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CustomerTests {

  @Test
  public void customerClassProperlyDecorated_Test () {
    assertNotNull(Customer.class.getAnnotation(javax.persistence.Entity.class));
  }

  @Test
  public void idFieldProperlyDecorated_Test () throws Exception {
    Field field = Customer.class.getDeclaredField("id");
    assertNotNull(field.getAnnotation(javax.persistence.Id.class));
    GeneratedValue generatedValueAnnotation = field.getAnnotation(GeneratedValue.class);
    assertNotNull(generatedValueAnnotation);
    assertEquals(generatedValueAnnotation.strategy(), GenerationType.AUTO);
  }


}
