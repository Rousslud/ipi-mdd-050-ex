package com.ipiecoles.java.mdd050.exception;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@RestControllerAdvice
	public class GlobalExceptionHandler 
	  extends ResponseEntityExceptionHandler {

	  @ExceptionHandler(EntityNotFoundException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  public String handleEntityNotFoundException(
	    EntityNotFoundException entityNotFoundException) {
	      return entityNotFoundException.getMessage();
	  }
	  
	  @ExceptionHandler(IllegalArgumentException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  public String handleEIllegalArgumentException(
			  IllegalArgumentException e) {
	      return e.getMessage();
	  }
	  
	  @ExceptionHandler(PropertyReferenceException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  public String handlePropertyReferencetException(
			  PropertyReferenceException e) {
	      return "La propriété " + e.getPropertyName() + " n'existe pas !";
	  }
	  
	  @ExceptionHandler(ConflictException.class)
	  @ResponseStatus(HttpStatus.CONFLICT)
	  public String handleConflictException(
	    ConflictException e) {
	      return e.getMessage();
	  }
	}

