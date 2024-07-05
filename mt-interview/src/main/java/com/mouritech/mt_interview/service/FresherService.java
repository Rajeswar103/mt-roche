package com.mouritech.mt_interview.service;

import com.mouritech.mt_interview.dto.FresherDto;
import com.mouritech.mt_interview.exceptions.DeatilsNotFoundException;
import com.mouritech.mt_interview.exceptions.NotElgibleException;

public interface FresherService {
	
	public Object saveFresherDetails(FresherDto fresherDto) throws DeatilsNotFoundException,NotElgibleException;
	public Object getAllFresherDetails(Integer page,Integer size);
	public Object deleteByid(Integer id);
	public Object getById(Integer id);

}
