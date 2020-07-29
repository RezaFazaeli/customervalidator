package com.adclear.customervalidator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyStatisticsDto {

    private Long customerId;

    private String customerName;

    private long totalRequests;

    private List<HourlyStatsDto> hourlyStats;

}
