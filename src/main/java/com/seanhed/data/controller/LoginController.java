package com.seanhed.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seanhed.beans.ClientType;
import com.seanhed.data.service.AdminServiceImpl;
import com.seanhed.data.service.CompanyServiceImpl;
import com.seanhed.data.service.CustomerServiceImpl;
import com.seanhed.utils.ResponseUtil;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

	@Autowired
	private AdminServiceImpl adminService;

	@Autowired
	private CompanyServiceImpl companyService;

	@Autowired
	private CustomerServiceImpl customerService;

	// http://localhost:8080/login
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestParam String name, @RequestParam String password,
			@RequestParam ClientType clientType) {
		switch (clientType) {
		case ADMINISTRATOR:
			return adminService.login(name, password, clientType);
		case COMPANY:
			return companyService.login(name, password, clientType);
		case CUSTOMER:
			return customerService.login(name, password, clientType);
		}
		return ResponseUtil.generateErrorCode(400, "oh no kennys dead");
	}
	
	// http://localhost:8080/logout
	@PostMapping("/blogout")
	public ResponseEntity<Object> logout(@RequestParam String token, @RequestParam ClientType clientType) {
		switch (clientType) {
		case ADMINISTRATOR:
			return adminService.logout(token);
		case COMPANY:
			return companyService.logout(token);
		case CUSTOMER:
			return customerService.logout(token);
		}
		return ResponseUtil.generateErrorCode(400, "oh no kennys dead");
	}
	
	
	
	
	
}
