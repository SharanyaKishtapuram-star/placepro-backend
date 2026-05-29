package com.placepro.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import com.placepro.service.DashboardService;
import com.placepro.repository.UserRepository;

@RestController
public class DashboardController {

    

	private final DashboardService dashboardService;

	public DashboardController(
	        DashboardService dashboardService) {

	    this.dashboardService = dashboardService;
	}

	@GetMapping("/api/dashboard/stats")
	public Map<String, Long> getDashboardStats(
	        Authentication authentication) {

	    return dashboardService
	            .getDashboardStats(authentication);
	}}