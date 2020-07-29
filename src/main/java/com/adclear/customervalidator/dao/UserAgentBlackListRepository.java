package com.adclear.customervalidator.dao;

import com.adclear.customervalidator.entity.UserAgentBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAgentBlackListRepository extends JpaRepository<UserAgentBlacklist, String> {
}
