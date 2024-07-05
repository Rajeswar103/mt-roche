package com.mouritech.mt_interview.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouritech.mt_interview.model.Fresher;

@Repository
public interface FresherRepository  extends JpaRepository<Fresher,Integer>{

}
