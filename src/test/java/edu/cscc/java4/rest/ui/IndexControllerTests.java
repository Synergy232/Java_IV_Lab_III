package edu.cscc.java4.rest.ui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author Jeff Anderson
 * @since 2019-04-20
 */

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = IndexController.class)
public class IndexControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void indexPageDisplays_Test () throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.view().name("index"));
  }

  @Test
  public void locationsPageDisplays_Test () throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/locations")).andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.view().name("locations"));
  }

  @Test
  public void aboutPageDisplays_Test () throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/about")).andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.view().name("about"));
  }


}
