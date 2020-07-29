package com.adclear.customervalidator.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@SuperBuilder
@Entity
@NoArgsConstructor
@Table(name = "ua_blacklist")
public class UserAgentBlacklist {

    @Id
    private String userAgentID;

}
