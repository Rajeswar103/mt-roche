package com.mouritech.mt_interview.interviewremainder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mouritech.mt_interview.dao.FresherRepository;
import com.mouritech.mt_interview.model.Fresher;

@Component
public class FresherInterviewRemainder {
	
	
	@Autowired
	private FresherRepository fresherRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	//@Scheduled(fixedDelay = 2000)
	@Scheduled(cron = "0 15 10 14 07 ?")
	public  void schuduleRemainder()
	{
		 List<Fresher> list = fresherRepository.findAll();
		for(Fresher s:list)
		{
			SimpleMailMessage mailMessage=new SimpleMailMessage();
			mailMessage.setFrom("rajeswarb.in@mouritech.com");
			mailMessage.setTo(s.getEmail());
			mailMessage.setSubject("Joining Remainder");
			mailMessage.setText("Your Interview is Tomorrow Pls be Available");
			javaMailSender.send(mailMessage);
		}
	}

}
