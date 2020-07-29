package com.adclear.customervalidator.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.adclear.customervalidator.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class CustomerControllerTest {

  @Autowired
  private WebApplicationContext context;

  private MockMvc mvc;

  @Before
  public void setUp() {
    this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
  }

  @Test
  public void validateCustomer_Should_Throw_InvalidCustomerException() throws Exception {
    String request =  "{\"customerId\": \"1\",\"tagId\": 500,\"userId\": \"reza123\",\"remoteIP\": \"0\",\"timestamp\": 1500000000}";
    MvcResult mvcResult = mvc.perform(post("/customer/validate").contentType(MediaType.APPLICATION_JSON)
        .content(request))
        .andExpect(status().isBadRequest()).andReturn();
    mvcResult.getResponse().getContentAsString();
  }

  @Test
  public void validateCustomer_Should_Be_OK() throws Exception {
    String request =  "{\"customerId\": \"1\",\"tagId\": 500,\"userId\": \"reza123\",\"remoteIP\": \"172.20.120.145\",\"timestamp\": 1500000000}";
    MvcResult mvcResult = mvc.perform(post("/customer/validate").contentType(MediaType.APPLICATION_JSON)
        .content(request))
        .andExpect(status().isOk()).andReturn();
    mvcResult.getResponse().getContentAsString();
  }

}
