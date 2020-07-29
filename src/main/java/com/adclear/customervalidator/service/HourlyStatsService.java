package com.adclear.customervalidator.service;

import com.adclear.customervalidator.dto.CustomerRequestDto;
import com.adclear.customervalidator.entity.Customer;
import com.adclear.customervalidator.entity.HourlyStats;
import com.adclear.customervalidator.dao.HourlyStatsRepository;

import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HourlyStatsService {

    private final HourlyStatsRepository hourlyStatsRepository;

    public HourlyStats save(HourlyStats hourlyStats) {
        return hourlyStatsRepository.save(hourlyStats);
    }

    public HourlyStats save(CustomerRequestDto dto) {
        LocalDateTime start = LocalDateTime.of(dto.getTimestamp().getYear(), dto.getTimestamp().getMonth(),
                dto.getTimestamp().getDayOfMonth(), dto.getTimestamp().getHour(), 0);
        LocalDateTime end = start.withMinute(59).withSecond(59);
        HourlyStats hourlyStats =
                hourlyStatsRepository.findByCustomerIdAndTimeBetween(dto.getCustomerId(), start, end)
                        .orElseGet(HourlyStats::new);
        if (hourlyStats.getId() == null) {
            hourlyStats.setCustomer(Customer.builder().id(dto.getCustomerId()).build());
        }
        hourlyStats.setTime(dto.getTimestamp());
        if (dto.isValid()) {
            hourlyStats.setRequestCount(hourlyStats.getRequestCount() + 1L);
        } else {
            hourlyStats.setInvalidCount(hourlyStats.getInvalidCount() + 1L);
        }
        return save(hourlyStats);
    }

    public List<HourlyStats> getDailyStatisticsByCustomerId(long customerId,
                                                            LocalDateTime start) {
        LocalDateTime end = start.withHour(23).withMinute(59).withSecond(59);
        return hourlyStatsRepository.findAllByCustomerIdAndTimeBetweenOrderByTimeAsc(customerId, start, end);
    }

    public long getTotalRequest(List<HourlyStats> hourlyStats) {
        return hourlyStats.stream()
                .map(x -> x.getInvalidCount() + x.getRequestCount())
                .collect(Collectors.summingInt(Long::intValue));
    }
}
