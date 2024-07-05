package com.mouritech.mt_interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.mouritech.mt_interview.controller.FresherController;
import com.mouritech.mt_interview.dto.AdminDto;
import com.mouritech.mt_interview.dto.FresherDto;
import com.mouritech.mt_interview.dto.FresherPersonalDetailsDto;
import com.mouritech.mt_interview.exceptions.DeatilsNotFoundException;
import com.mouritech.mt_interview.exceptions.NotElgibleException;
import com.mouritech.mt_interview.exceptions.UserNameExists;
import com.mouritech.mt_interview.exceptions.UserNotFound;
import com.mouritech.mt_interview.model.AdminRequest;
import com.mouritech.mt_interview.model.Fresher;
import com.mouritech.mt_interview.model.FresherPersonalDetails;
import com.mouritech.mt_interview.serviceimpl.AdminJwtService;
import com.mouritech.mt_interview.serviceimpl.AdminserviceImpl;
import com.mouritech.mt_interview.serviceimpl.FresherserviceImpl;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class FresherControllerTest {
	
	
	@InjectMocks
	private FresherController fresherController;
	
	@Mock
    private AuthenticationManager authManager;

    @Mock
    private AdminJwtService jwtService;

    @Mock
    private org.springframework.security.core.Authentication authentication;
	
	
	@Mock
	private AdminserviceImpl adminServiceImpl;

	@Mock
	private FresherserviceImpl fresherserviceImpl;
	
	private Fresher fresher;
	private FresherDto fresherDto;
	private FresherPersonalDetails fresherPersonalDetails;
	private FresherPersonalDetailsDto fresherPersonalDetailsDto;
	private AdminDto adminDto;
	private AdminRequest adminRequest;

	
	
	@BeforeEach
	public void setUp() {
		
		fresher=new Fresher();
		fresher.setId(1);
		fresher.setEmail("abc@gmail.com");
		fresher.setGraduationPercentage(90);
		fresher.setName("abc");
		fresher.setFresherPersonalDetails(fresherPersonalDetails);
		fresherPersonalDetails=new FresherPersonalDetails();
		fresherPersonalDetails.setAddress("kadapa");
		fresherPersonalDetails.setId(1);
		fresherPersonalDetails.setFatherName("abc mother");
		fresherPersonalDetails.setMotherName("abc mother");
		fresherDto=new FresherDto();
		fresherDto.setId(1);
		fresherDto.setEmail("abc@gmail.com");
		fresherDto.setGraduationPercentage(90);
		fresherDto.setName("abc");
		fresherDto.setFresherPersonalDetailsDto(fresherPersonalDetailsDto);
		fresherPersonalDetailsDto=new FresherPersonalDetailsDto();
		fresherPersonalDetailsDto.setAddress("kadapa");
		fresherPersonalDetailsDto.setId(1);
		fresherPersonalDetailsDto.setFatherName("abc mother");
		fresherPersonalDetailsDto.setMotherName("abc mother");
		adminDto=new AdminDto();
		adminDto.setAccessPassword("abc@123");
		adminDto.setId(1);
		adminDto.setPassword("abc");
		adminDto.setUsername("rajesh");
		adminDto.setRole("role");
		adminRequest=new AdminRequest();
		adminRequest.setUsername("abc");
		adminRequest.setPassword("password");
		
		}
	@Test
	@Order(1)
	public void testSaveFreshers() throws DeatilsNotFoundException, NotElgibleException {
		when(fresherserviceImpl.saveFresherDetails(fresherDto)).thenReturn(fresherDto);
		ResponseEntity<?> response = fresherController.saveFresherDetails(fresherDto);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	@Test
	@Order(2)
	public void testgetById()  {

		Integer id = 1;
		when(fresherserviceImpl.getById(id)).thenReturn(fresher);
		ResponseEntity<?> response = fresherController.getById(id);
		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}

	@Test
	@Order(3)
	public void testDeleteById() {
		Integer id = 1;
		when(fresherserviceImpl.deleteByid(id)).thenReturn("Deleted Successfully...............");
		ResponseEntity<?> response = fresherController.deleteById(id);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	@Test
	@Order(4)
	public void saveAdmin() throws UserNameExists
	{
		when(adminServiceImpl.saveAdminDetails(adminDto)).thenReturn(adminDto);
		ResponseEntity<?> response = fresherController.saveAdminDetails(adminDto);
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}
	@Test
	@Order(5)
    public void testGetAllFreshers() {
        List<FresherDto> fresherDtoList = Collections.singletonList(fresherDto);

        when(fresherserviceImpl.getAllFresherDetails(anyInt(), anyInt())).thenReturn(fresherDtoList);

        ResponseEntity<?> responseEntity = fresherController.getAllFreshers(0, 10);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        
    }
	   @Order(6)
	   @Test
	    public void testGenerateToken_Success() throws UserNotFound {
	        when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
	        when(authentication.isAuthenticated()).thenReturn(true);
	        when(jwtService.generateTokens(adminRequest.getUsername())).thenReturn("fake-jwt-token");

	        ResponseEntity<?> responseEntity = fresherController.generateToken(adminRequest);

	        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	        assertEquals("fake-jwt-token", responseEntity.getBody());
	    }
	   @Order(7)
	   @Test
	    public void testGenerateToken_Failure() {
	        when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
	        when(authentication.isAuthenticated()).thenReturn(false);

	        assertThrows(UserNotFound.class, () -> {
	            fresherController.generateToken(adminRequest);
	        });
	    }
	

	
	
	

}
