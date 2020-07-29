package com.adclear.customervalidator.dao;

import com.adclear.customervalidator.entity.HourlyStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface HourlyStatsRepository extends JpaRepository<HourlyStats, Long> {

    Optional<HourlyStats> findByCustomerIdAndTimeBetween(long customerId, LocalDateTime Start, LocalDateTime end);

    List<HourlyStats> findAllByCustomerIdAndTimeBetweenOrderByTimeAsc(long customerId, LocalDateTime Start, LocalDateTime end);
}
