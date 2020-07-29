package com.adclear.customervalidator.controller;

import com.adclear.customervalidator.dto.DailyStatisticsDto;
import com.adclear.customervalidator.entity.HourlyStats;
import com.adclear.customervalidator.service.HourlyStatsService;
import com.adclear.customervalidator.utils.HourlyStatsMapper;
import io.swagger.annotations.ApiOperation;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

  private final HourlyStatsService hourlyStatsSrv;

  private HourlyStatsMapper hourlyStatsMapper = HourlyStatsMapper.INSTANCE;

  @GetMapping("/dailyStatistics/{customerId}/{date}")
  @ApiOperation(value = "To get statistics of a customer per hour and total valid and invalid requests about that customer")
  public DailyStatisticsDto getDailyStatisticsByCustomer(@PathVariable long customerId,
                                                         @PathVariable
                                                         @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                             Date date) {
    List<HourlyStats> listHourlyStats = hourlyStatsSrv.getDailyStatisticsByCustomerId(customerId,
        LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()),
            TimeZone.getDefault().toZoneId()));

    DailyStatisticsDto dailyStatisticsDto = DailyStatisticsDto.builder()
        .hourlyStats(hourlyStatsMapper.toHourlyStatsDto(listHourlyStats))
        .totalRequests(hourlyStatsSrv.getTotalRequest(listHourlyStats)).build();

    if (!CollectionUtils.isEmpty(listHourlyStats)) {
      dailyStatisticsDto.setCustomerId(customerId);
      dailyStatisticsDto.setCustomerName(listHourlyStats.get(0).getCustomer().getName());
    }
    return dailyStatisticsDto;
  }

}
