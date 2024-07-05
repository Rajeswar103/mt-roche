package com.mouritech.mt_interview.serviceimpl;

import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mouritech.mt_interview.dao.FresherRepository;
import com.mouritech.mt_interview.dto.FresherDto;
import com.mouritech.mt_interview.dto.FresherPersonalDetailsDto;
import com.mouritech.mt_interview.exceptions.DeatilsNotFoundException;
import com.mouritech.mt_interview.exceptions.NotElgibleException;
import com.mouritech.mt_interview.model.Fresher;
import com.mouritech.mt_interview.model.FresherPersonalDetails;
import com.mouritech.mt_interview.service.FresherService;

@Service
public class FresherserviceImpl implements FresherService {
	
	
	@Autowired
	private FresherRepository fresherRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public Object saveFresherDetails(FresherDto fresherDto) throws DeatilsNotFoundException, NotElgibleException {
		
		Fresher fresher = mapToEntity(fresherDto);
		if(fresher.getGraduationPercentage()<80)
		{
			throw new NotElgibleException();
		}
		Object response = fresherRepository.save(fresher);
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		mailMessage.setFrom("rajeswarb.in@mouritech.com");
		mailMessage.setSubject("eligible for Mouri Tech Interview");
		mailMessage.setTo(fresher.getEmail());
		mailMessage.setText("your are eligible for these interview your interview slot is 15-07-2024 10.AM");
		javaMailSender.send(mailMessage);
		return "Your are Eligible for these interview pls check your email for interview slot";
	}
	
	private Fresher mapToEntity(FresherDto fresherDto) throws DeatilsNotFoundException
	{
		Fresher fresher=new Fresher();
		fresher.setId(fresherDto.getId());
		fresher.setEmail(fresherDto.getEmail());
		fresher.setName(fresherDto.getName());
		fresher.setGraduationPercentage(fresherDto.getGraduationPercentage());
		if(fresherDto.getFresherPersonalDetailsDto()!=null)
		{
			fresher.setFresherPersonalDetails(mapToEntity(fresherDto.getFresherPersonalDetailsDto()));
		}
		else
		{
			throw new DeatilsNotFoundException();
		}
		
		return fresher;
		
	}
	
	private FresherPersonalDetails mapToEntity(FresherPersonalDetailsDto fresherPersonalDetailsDto) {
		
		return modelMapper.map(fresherPersonalDetailsDto, FresherPersonalDetails.class);
	}

	@Override
	public Object getAllFresherDetails(Integer page, Integer size) {
		PageRequest pageble=PageRequest.of(page,size);
		List<Fresher> list = fresherRepository.findAll();
		return list;
	}

	@Override
	public Object deleteByid(Integer id) {
		if(fresherRepository.findById(id).isEmpty())
		{
			return "id is not found";
		}
		fresherRepository.deleteById(id);
		return "deleted sucessfully";
	}

	@Override
	public Object getById(Integer id) {
		if(fresherRepository.findById(id).isEmpty())
		{
			return "id is not found";
		}
		
		return fresherRepository.findById(id);
	}

	

}
