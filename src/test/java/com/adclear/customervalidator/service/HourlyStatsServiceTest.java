package com.adclear.customervalidator.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;


import com.adclear.customervalidator.dao.CustomerRepository;
import com.adclear.customervalidator.dao.HourlyStatsRepository;
import com.adclear.customervalidator.dao.IpBlacklistRepository;
import com.adclear.customervalidator.dao.UserAgentBlackListRepository;
import com.adclear.customervalidator.dto.CustomerRequestDto;
import com.adclear.customervalidator.entity.Customer;
import com.adclear.customervalidator.entity.HourlyStats;
import com.adclear.customervalidator.exception.CustomerNotFoundException;
import com.adclear.customervalidator.exception.InvalidCustomerException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HourlyStatsServiceTest {


  @InjectMocks
  private HourlyStatsService hourlyStatsSrv;

  @Mock
  private HourlyStatsRepository hourlyStatsRepository;

  @Test
  public void hourlyStats_Test_Save_Should_Be_OK() {

    //GIVEN
    HourlyStats hourlyStats = HourlyStats.builder().build();
    Optional<HourlyStats> optionalHourlyStats = Optional.of(hourlyStats);

    doReturn(optionalHourlyStats).when(hourlyStatsRepository)
        .findByCustomerIdAndTimeBetween(any(Long.class), any(
            LocalDateTime.class), any(LocalDateTime.class));

    doReturn(hourlyStats).when(hourlyStatsRepository)
        .save(any(HourlyStats.class));

    //WHEN
    HourlyStats hourlyStatsSave = hourlyStatsSrv
        .save(CustomerRequestDto.builder().customerId(1L).timestamp(LocalDateTime.now()).build());

    //THEN
    assertTrue(hourlyStatsSave != null);


  }

  @Test
  public void getDailyStatisticsByCustomerId_Test_Should_Be_OK() {
    //GIVEN
    HourlyStats hourlyStats = HourlyStats.builder().build();
    HourlyStats hourlyStats1 = HourlyStats.builder().build();

    List<HourlyStats> hourlyStatsList = Arrays.asList(hourlyStats, hourlyStats1);

    //WHEN
    doReturn(hourlyStatsList).when(hourlyStatsRepository)
        .findAllByCustomerIdAndTimeBetweenOrderByTimeAsc(any(Long.class), any(
            LocalDateTime.class), any(LocalDateTime.class));

    List<HourlyStats> hourlyStats2 =
        hourlyStatsSrv.getDailyStatisticsByCustomerId(1, LocalDateTime.now());

    //THEN
    assertTrue(hourlyStats2.size() == 2);
  }


}
