package com.adclear.customervalidator.dao;

import com.adclear.customervalidator.entity.IpBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IpBlacklistRepository extends JpaRepository<IpBlacklist, Long> {
}
