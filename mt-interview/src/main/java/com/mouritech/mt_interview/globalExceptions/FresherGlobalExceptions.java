package com.mouritech.mt_interview.globalExceptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mouritech.mt_interview.exceptions.DeatilsNotFoundException;
import com.mouritech.mt_interview.exceptions.NotElgibleException;
import com.mouritech.mt_interview.exceptions.UserNameExists;
import com.mouritech.mt_interview.exceptions.UserNotFound;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class FresherGlobalExceptions {
	
	
	@ExceptionHandler(DeatilsNotFoundException.class)
    public ResponseEntity<String> handleExceptions(DeatilsNotFoundException exception)
    {
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    	
    }
	@ExceptionHandler(NotElgibleException.class)
    public ResponseEntity<String> handleExceptions(NotElgibleException exception)
    {
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    	
    }
	@ExceptionHandler(UserNameExists.class)
    public ResponseEntity<String> handleExceptions(UserNameExists exception)
    {
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    	
    }
	@ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleExceptions(UsernameNotFoundException exception)
    {
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    	
    }
	@ExceptionHandler(UserNotFound.class)
    public ResponseEntity<String> handleExceptions(UserNotFound exception)
    {
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    	
    }

    @ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String,String>> handleConstraintViolationException(ConstraintViolationException e)
	{
		
		Set<ConstraintViolation<?>> c=e.getConstraintViolations();
		Map<String,String> mapResults=new HashMap<>();
		
		for(ConstraintViolation<?> e1:c)
		{
			String s1=e1.getPropertyPath().toString();
			String s2=e1.getMessage();
			mapResults.put(s1,s2);
		}
		return new ResponseEntity<Map<String,String>>(mapResults,HttpStatus.BAD_REQUEST);
		
	}
    

}
