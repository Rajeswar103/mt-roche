package com.mouritech.mt_interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import com.mouritech.mt_interview.dao.FresherRepository;
import com.mouritech.mt_interview.dto.FresherDto;
import com.mouritech.mt_interview.dto.FresherPersonalDetailsDto;
import com.mouritech.mt_interview.exceptions.DeatilsNotFoundException;
import com.mouritech.mt_interview.exceptions.NotElgibleException;
import com.mouritech.mt_interview.model.Fresher;
import com.mouritech.mt_interview.model.FresherPersonalDetails;
import com.mouritech.mt_interview.serviceimpl.FresherserviceImpl;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class MtInterviewApplicationTests {

	@InjectMocks
    private FresherserviceImpl fresherService;

    @Mock
    private FresherRepository fresherRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private JavaMailSender javaMailSender;

    private FresherDto fresherDto;
    private Fresher fresher;
    @BeforeEach
    public void setUp() {
        FresherPersonalDetailsDto personalDetailsDto = new FresherPersonalDetailsDto();
        personalDetailsDto.setId(1);
        personalDetailsDto.setAddress("abc Address");
        fresherDto = new FresherDto();
        fresherDto.setId(1);
        fresherDto.setName("abc");
        fresherDto.setEmail("abc@example.com");
        fresherDto.setGraduationPercentage(85);
        fresherDto.setFresherPersonalDetailsDto(personalDetailsDto);
        FresherPersonalDetails personalDetails = new FresherPersonalDetails();
        personalDetails.setId(1);
        personalDetails.setAddress("abc Address");
        fresher = new Fresher();
        fresher.setId(1);
        fresher.setName("abc");
        fresher.setEmail("abc@example.com");
        fresher.setGraduationPercentage(85);
        fresher.setFresherPersonalDetails(personalDetails);
    }
    @Order(1)
    @Test
    public void testSaveFresherDetails() throws DeatilsNotFoundException, NotElgibleException {
        when(modelMapper.map(any(FresherPersonalDetailsDto.class), any(Class.class)))
                .thenReturn(new FresherPersonalDetails());
        when(fresherRepository.save(any(Fresher.class))).thenReturn(fresher);

        String response = (String) fresherService.saveFresherDetails(fresherDto);

        assertEquals("Your are Eligible for these interview pls check your email for interview slot", response);
    }
    @Order(2)
    @Test
    public void testSaveFresherDetailsNotEligible() {
        fresherDto.setGraduationPercentage(70);

        assertThrows(NotElgibleException.class, () -> {
            fresherService.saveFresherDetails(fresherDto);
        });
    }
    @Order(3)
    @Test
    public void testGetAllFresherDetails() {
        when(fresherRepository.findAll()).thenReturn(List.of(fresher));

        List<Fresher> fresherList = (List<Fresher>) fresherService.getAllFresherDetails(0, 10);

        assertEquals(1, fresherList.size());
        assertEquals(fresher.getName(), fresherList.get(0).getName());
    }
    @Order(4)
    @Test
    public void testDeleteById() {
        when(fresherRepository.findById(1)).thenReturn(Optional.of(fresher));

        String response = (String) fresherService.deleteByid(1);

        assertEquals("deleted sucessfully", response);
    }
    @Order(5)
    @Test
    public void testDeleteByIdNotFound() {
        when(fresherRepository.findById(1)).thenReturn(Optional.empty());

        String response = (String) fresherService.deleteByid(1);

        assertEquals("id is not found", response);
    }
    @Order(6)
    @Test
    public void testGetById() {
        when(fresherRepository.findById(1)).thenReturn(Optional.of(fresher));

        Optional<Fresher> foundFresher = (Optional<Fresher>) fresherService.getById(1);

        assertEquals(fresher.getName(), foundFresher.get().getName());
    }
    @Order(7)
    @Test
    public void testGetByIdNotFound() {
        when(fresherRepository.findById(1)).thenReturn(Optional.empty());

        String response = (String) fresherService.getById(1);

        assertEquals("id is not found", response);
    }

}
