package com.adclear.customervalidator.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;


import com.adclear.customervalidator.dao.CustomerRepository;
import com.adclear.customervalidator.dao.IpBlacklistRepository;
import com.adclear.customervalidator.dao.UserAgentBlackListRepository;
import com.adclear.customervalidator.dto.CustomerRequestDto;
import com.adclear.customervalidator.entity.Customer;
import com.adclear.customervalidator.entity.HourlyStats;
import com.adclear.customervalidator.exception.CustomerNotFoundException;
import com.adclear.customervalidator.exception.InvalidCustomerException;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

  @InjectMocks
  private CustomerService customerService;

  @Mock
  private CustomerRepository customerRepository;

  @Mock
  private HourlyStatsService hourlyStatsSrv;

  @Mock
  private IpBlacklistRepository ipBlacklistRepository;

  @Mock
  private UserAgentBlackListRepository userAgentBlackListRepository;

  @Test(expected = CustomerNotFoundException.class)
  public void validateCustomer_Test_should_Throw_CustomerNotFoundException() {

    //GIVEN
    Optional<Customer> optionalCustomer = Optional.empty();
    doReturn(optionalCustomer).when(customerRepository).findById(any(Long.class));

    //WHEN
    boolean result = customerService.checkValidateCustomer(CustomerRequestDto.builder()
        .customerId(1L)
        .userId("1")
        .remoteIP(1234L)
        .build());

    //THEN
    assertTrue(result == true);
  }

  @Test
  public void validateCustomer_Test_should_be_ok_With_valid_Customer() {

    //GIVEN
    Customer customer = Customer.builder().id((long) 1).active(true).name("name").build();
    Optional<Customer> optionalCustomer = Optional.of(customer);

    HourlyStats hourlyStats1 = HourlyStats.builder().build();

    doReturn(optionalCustomer).when(customerRepository).findById(any(Long.class));
    doReturn(false).when(ipBlacklistRepository).existsById(any(Long.class));
    doReturn(false).when(userAgentBlackListRepository).existsById(any(String.class));
    doReturn(hourlyStats1).when(hourlyStatsSrv).save(any(CustomerRequestDto.class));

    //WHEN
    boolean result = customerService.checkValidateCustomer(CustomerRequestDto.builder()
        .customerId(1L)
        .userId("1")
        .remoteIP(1234L)
        .build());

    //THEN
    assertTrue(result == true);

  }

  @Test(expected = InvalidCustomerException.class)
  public void validateCustomer_Test_should_Throw_InvalidCustomerException_With_Invalid_Customer() {

    //GIVEN
    Customer customer = Customer.builder().id((long) 1).active(false).name("name").build();
    Optional<Customer> optionalCustomer = Optional.of(customer);

    HourlyStats hourlyStats1 = HourlyStats.builder().build();

    doReturn(optionalCustomer).when(customerRepository).findById(any(Long.class));
    doReturn(hourlyStats1).when(hourlyStatsSrv).save(any(CustomerRequestDto.class));

    //WHEN
    boolean result = customerService.checkValidateCustomer(CustomerRequestDto.builder()
        .customerId(1L)
        .userId("1")
        .remoteIP(1234L)
        .build());

    //THEN
    assertTrue(result == false);

  }
}
