package com.adclear.customervalidator.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@Entity
@NoArgsConstructor
@Table(name = "ip_blacklist")
public class IpBlacklist {

    @Id
    private Long ip;

}
