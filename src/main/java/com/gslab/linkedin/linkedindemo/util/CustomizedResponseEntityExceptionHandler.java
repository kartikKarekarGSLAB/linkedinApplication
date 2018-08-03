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
import com.gslab.linkedin.linkedindemo.model.vo.ResponseBase;
import com.gslab.linkedin.linkedindemo.model.vo.Status;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(InvalidUserInputException.class)
	public final ResponseEntity<ResponseBase> handleUserNotFoundException(InvalidUserInputException ex,
			WebRequest request) {
		ResponseBase errorResponseBase = new ResponseBase(new Status(406, ex.getMessage(),
				messageSource.getMessage("exception.invaliduserinput", null, Locale.US)));
		return new ResponseEntity<>(errorResponseBase, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(CRUDOperationFailureException.class)
	public final ResponseEntity<ResponseBase> handleCRUDOperationFailureException(CRUDOperationFailureException ex,
			WebRequest request) {
		ResponseBase errorResponseBase = new ResponseBase(new Status(406, ex.getMessage(),
				messageSource.getMessage("exception.invalidcrudoperation", null, Locale.US)));
		return new ResponseEntity<>(errorResponseBase, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ResponseBase> handleAllExceptions(Exception ex, WebRequest request) {
		ex.printStackTrace();
		ResponseBase errorResponseBase = new ResponseBase(
				new Status(500, ex.getMessage(), ex.getStackTrace().toString()));
		return new ResponseEntity<>(errorResponseBase, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}