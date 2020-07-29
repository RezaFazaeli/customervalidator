package com.adclear.customervalidator.service;

import com.adclear.customervalidator.dao.IpBlacklistRepository;
import com.adclear.customervalidator.dao.UserAgentBlackListRepository;
import com.adclear.customervalidator.dto.CustomerRequestDto;
import com.adclear.customervalidator.entity.Customer;
import com.adclear.customervalidator.exception.CustomerNotFoundException;
import com.adclear.customervalidator.dao.CustomerRepository;
import com.adclear.customervalidator.exception.InvalidCustomerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepo;

  private final HourlyStatsService hourlyStatsSrv;

  private final IpBlacklistRepository ipBlacklistRepository;

  private final UserAgentBlackListRepository userAgentBlackListRepository;

  @Transactional(noRollbackFor = InvalidCustomerException.class)
  public boolean checkValidateCustomer(CustomerRequestDto CustomerRequestDto) {
    Customer customer = customerRepo.findById(CustomerRequestDto.getCustomerId())
        .orElseThrow(() -> new CustomerNotFoundException());
    boolean isValid = customer.isActive()
        && !ipBlacklistRepository.existsById(CustomerRequestDto.getRemoteIP())
        && !userAgentBlackListRepository.existsById(CustomerRequestDto.getUserId());
    CustomerRequestDto.setValid(isValid);
    hourlyStatsSrv.save(CustomerRequestDto);
    if (!isValid) {
      throw new InvalidCustomerException();
    }
    return true;
  }

}