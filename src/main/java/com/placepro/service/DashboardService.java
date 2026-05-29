package com.placepro.service;

import java.util.Map;
import org.springframework.security.core.Authentication;

public interface DashboardService {

    Map<String, Long> getDashboardStats(
            Authentication authentication
    );

    long getRecruiterJobCount(String email);
    Long getRecruiterTotalJobs(String email);
}