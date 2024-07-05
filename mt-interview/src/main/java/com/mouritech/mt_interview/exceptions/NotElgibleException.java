package com.mouritech.mt_interview.exceptions;

public class NotElgibleException extends Exception {
	
	
	
	
	public String getMessage()
	{
		String reponse="{\"your are not eligible for these interview\"}";
		return reponse;
		
	}

}
