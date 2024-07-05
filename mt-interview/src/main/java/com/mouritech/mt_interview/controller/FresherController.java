package com.mouritech.mt_interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mouritech.mt_interview.dto.AdminDto;
import com.mouritech.mt_interview.dto.FresherDto;
import com.mouritech.mt_interview.exceptions.DeatilsNotFoundException;
import com.mouritech.mt_interview.exceptions.NotElgibleException;
import com.mouritech.mt_interview.exceptions.UserNameExists;
import com.mouritech.mt_interview.exceptions.UserNotFound;
import com.mouritech.mt_interview.model.AdminRequest;
import com.mouritech.mt_interview.service.AdminService;
import com.mouritech.mt_interview.service.FresherService;
import com.mouritech.mt_interview.serviceimpl.AdminJwtService;

@Controller
public class FresherController {
	
	@Autowired
	private FresherService fresherService;
	
	@Autowired
	private AdminService adminService;
	
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private AdminJwtService jwtService;
	
	
	@PostMapping("/savefresherdetails")
	public ResponseEntity<?> saveFresherDetails(@RequestBody FresherDto fresherDto) throws DeatilsNotFoundException, NotElgibleException
	{
		return new ResponseEntity<Object>(fresherService.saveFresherDetails(fresherDto),HttpStatus.OK);
		
	}
	@GetMapping("/getallFreshers")
	public ResponseEntity<?> getAllFreshers(@RequestParam(name = "page") Integer page,@RequestParam(name="size") Integer size)
	{
		return new ResponseEntity<Object>(fresherService.getAllFresherDetails(page, size),HttpStatus.OK);
		
	}
	@GetMapping("/getbyid")
	public ResponseEntity<?> getById(@RequestParam(name = "id")Integer id)
	{
		return new ResponseEntity<Object>(fresherService.getById(id),HttpStatus.OK);
		
	}
	@DeleteMapping("/deletebyid")
	public ResponseEntity<?> deleteById(@RequestParam(name = "id") Integer id)
	{
		return new ResponseEntity<Object>(fresherService.deleteByid(id),HttpStatus.OK);
	}
	
	@PostMapping("/adminregister")
	public ResponseEntity<?> saveAdminDetails(@RequestBody AdminDto adminDto) throws UserNameExists
	{
		return new ResponseEntity<Object>(adminService.saveAdminDetails(adminDto),HttpStatus.CREATED);
		
	}
	
	@PostMapping("/generatejwttoken")
	public ResponseEntity<?> generateToken(@RequestBody AdminRequest adminRequest) throws UserNotFound
	{
		Authentication auth = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(adminRequest.getUsername(), adminRequest.getPassword()));
		if (auth.isAuthenticated()) {
			return new ResponseEntity<String>(jwtService.generateTokens(adminRequest.getUsername()), HttpStatus.CREATED);
		}
		throw new UserNotFound();
		
	}

}
