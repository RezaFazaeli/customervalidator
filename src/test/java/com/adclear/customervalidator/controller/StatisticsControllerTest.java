package com.adclear.customervalidator.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.adclear.customervalidator.Application;
import com.adclear.customervalidator.dto.DailyStatisticsDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class StatisticsControllerTest {

  @Autowired
  private WebApplicationContext context;

  private MockMvc mvc;

  @Before
  public void setUp() {
    this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
  }

  @Test
  public void getDailyStatisticsByCustomer_Test_Should_Be_OK() throws Exception {
    MvcResult mvcResult = mvc.perform(get("/statistics/dailyStatistics/1/2020-07-28"))
        .andExpect(status().isOk()).andReturn();
    String responseJson = mvcResult.getResponse().getContentAsString();
    ObjectMapper objectMapper = new ObjectMapper();
    DailyStatisticsDto dailyStatisticsDto = objectMapper.readValue(responseJson, DailyStatisticsDto.class);
    assertThat(dailyStatisticsDto.getHourlyStats().size()).isEqualTo(0);
  }


}
