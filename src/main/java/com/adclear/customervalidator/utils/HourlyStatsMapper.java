package com.adclear.customervalidator.utils;

import com.adclear.customervalidator.dto.HourlyStatsDto;
import com.adclear.customervalidator.entity.HourlyStats;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface HourlyStatsMapper {

    HourlyStatsMapper INSTANCE = Mappers.getMapper(HourlyStatsMapper.class);

    HourlyStats toHourlyStats(HourlyStatsDto hourlyStatsDto);

    HourlyStatsDto toHourlyStatsDto(HourlyStats hourlyStats);

    default List<HourlyStatsDto> toHourlyStatsDto(List<HourlyStats> hourlyStats) {
        return hourlyStats.stream().map(stats -> (toHourlyStatsDto(stats))).collect(Collectors.toList());
    }
}