package com.gslab.linkedin.linkedindemo.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gslab.linkedin.linkedindemo.exception.CRUDOperationFailureException;
import com.gslab.linkedin.linkedindemo.exception.InvalidUserInputException;
import com.gslab.linkedin.linkedindemo.model.vo.ErrorBase;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(InvalidUserInputException.class)
	public final ResponseEntity<ErrorBase> handleUserNotFoundException(InvalidUserInputException ex,
			WebRequest request) {
		ErrorBase errorDetails = new ErrorBase(406, ex.getMessage(),
				messageSource.getMessage("exception.invaliduserinput", null, Locale.US));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(CRUDOperationFailureException.class)
	public final ResponseEntity<ErrorBase> handleCRUDOperationFailureException(CRUDOperationFailureException ex,
			WebRequest request) {
		ErrorBase errorDetails = new ErrorBase(406, ex.getMessage(),
				messageSource.getMessage("exception.invalidcrudoperation", null, Locale.US));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorBase> handleAllExceptions(Exception ex, WebRequest request) {
		ex.printStackTrace();
		ErrorBase errorDetails = new ErrorBase(500, ex.getMessage(), ex.getStackTrace().toString());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}