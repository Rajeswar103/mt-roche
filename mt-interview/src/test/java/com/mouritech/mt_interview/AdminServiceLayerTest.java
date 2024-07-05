package com.mouritech.mt_interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mouritech.mt_interview.dao.AdminRepository;
import com.mouritech.mt_interview.dto.AdminDto;
import com.mouritech.mt_interview.exceptions.UserNameExists;
import com.mouritech.mt_interview.model.Admin;
import com.mouritech.mt_interview.serviceimpl.AdminserviceImpl;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class AdminServiceLayerTest {
	
	@InjectMocks
    private AdminserviceImpl adminService;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ModelMapper modelMapper;

    private AdminDto adminDto;
    private Admin admin;

    @BeforeEach
    public void setUp() {
        adminDto = new AdminDto();
        adminDto.setId(1);
        adminDto.setUsername("admin");
        adminDto.setPassword("password");
        adminDto.setAccessPassword("Admin$%@!");
        adminDto.setRole("ADMIN");

        admin = new Admin();
        admin.setId(1);
        admin.setUsername("admin");
        admin.setPassword("encodedPassword");
        admin.setAccessPassword("Admin$%@!");
        admin.setRole("ADMIN");
    }

    
    @Order(1)
    @Test
    public void testSaveAdminDetailsSuccess() throws UserNameExists {
        when(adminRepository.findAll()).thenReturn(List.of());
        when(modelMapper.map(any(AdminDto.class), any(Class.class))).thenReturn(admin);
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");
        when(adminRepository.save(any(Admin.class))).thenReturn(admin);

        String response = (String) adminService.saveAdminDetails(adminDto);

        assertEquals("registered succesfull............", response);
    }
    @Order(2)
    @Test
    public void testSaveAdminDetailsUserNameExists() {
        when(adminRepository.findAll()).thenReturn(List.of(admin));

        assertThrows(UserNameExists.class, () -> {
            adminService.saveAdminDetails(adminDto);
        });
    }
    @Order(3)
    @Test
    public void testSaveAdminDetailsInvalidAccessPassword() throws UserNameExists {
        adminDto.setAccessPassword("InvalidPassword");

        when(adminRepository.findAll()).thenReturn(List.of());
        when(modelMapper.map(any(AdminDto.class), any(Class.class))).thenReturn(admin);

        String response = (String) adminService.saveAdminDetails(adminDto);

        assertEquals("your are not eligibele for these registration", response);
    }

}
