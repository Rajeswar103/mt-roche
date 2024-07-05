package com.mouritech.mt_interview.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouritech.mt_interview.model.FresherPersonalDetails;

@Repository
public interface FresherPersonalDetailsRepository extends JpaRepository<FresherPersonalDetails,Integer> {

}
