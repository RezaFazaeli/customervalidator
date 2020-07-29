package com.adclear.customervalidator.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@Entity
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(name = "unique_customer_time", columnNames = {"customer_id", "time"}))
public class HourlyStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne
    private Customer customer;

    @Column(nullable = false)
    private LocalDateTime time;

    @Column(nullable = false)
    private long requestCount;

    @Column(nullable = false)
    private long invalidCount;


}
