package pl.com.carfleetmanagementsystem.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/new")
	@PreAuthorize("hasRole('NEW') or hasRole('DRIVER') or hasRole('EMPLOYEE') or hasRole('BOSS') or hasRole('ADMIN')")
	public String newAccess() {
		return "New user Content.";
	}

	@GetMapping("/driver")
	@PreAuthorize("hasRole('DRIVER') or hasRole('EMPLOYEE') or hasRole('BOSS') or hasRole('ADMIN')")
	public String driverAccess() {
		return "Driver Board.";
	}

	@GetMapping("/employee")
	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('BOSS') or hasRole('ADMIN')")
	public String employeeAccess() {
		return "Employee Board.";
	}

	@GetMapping("/boss")
	@PreAuthorize("hasRole('BOSS') or hasRole('ADMIN')")
	public String bossAccess() {
		return "Boss Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
