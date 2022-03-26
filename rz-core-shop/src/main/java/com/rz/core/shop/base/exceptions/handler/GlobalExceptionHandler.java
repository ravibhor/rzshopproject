package com.rz.core.shop.base.exceptions.handler;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.rz.core.shop.base.BaseController;
import com.rz.core.shop.base.constants.HttpStatusCodes;
import com.rz.core.shop.base.enums.DefaultMessages;
import com.rz.core.shop.base.enums.UserMessages;
import com.rz.core.shop.base.exceptions.APIException;
import com.rz.core.shop.base.models.response.Response;
import com.rz.core.shop.utils.ValidationUtils;

@RestControllerAdvice
public class GlobalExceptionHandler extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	private static final String SEPARATOR = ", ";

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private HttpServletRequest request;

	@ExceptionHandler(APIException.class)
	public ResponseEntity<Response> handleApiException(APIException ex) {
		logger.error("handleApiException: ", ex);
		return createResponse(ex.getErrorConstant().getStatusCode(), ex.getErrorConstant().getMessage(), null, 0);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> handleException(Exception ex) {
		logger.error("handleException: ", ex);
		return createResponse(HttpStatusCodes.SERVER_INTERNAL_SERVER_ERROR, DefaultMessages.INTERNAL_SERVER_ERROR, null,
				0);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Response> handleMissingRequestBodyException(HttpMessageNotReadableException ex) {
		logger.error("Unable to parse request body: {}", ex.getMessage());
		return createResponse(HttpStatusCodes.CLIENT_ERROR_BAD_REQUEST, DefaultMessages.UNABLE_TO_PARSE_REQUEST_BODY,
				null, 0);
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<Response> handleFileSizeLimitExceededException1(MaxUploadSizeExceededException ex) {
		logger.error("handleFileSizeLimitExceededException1: {}", ex.getMessage());
		return createResponse(HttpStatusCodes.CLIENT_ERROR_BAD_REQUEST, DefaultMessages.INVALID_FILE_SIZE, null, 0);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Response> handleConstraintViolationException(ConstraintViolationException ex) {
		StringBuilder messages = new StringBuilder();
		String langHeader = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
		String locale = ValidationUtils.isEmpty(langHeader)
				? request.getAttribute(HttpHeaders.ACCEPT_LANGUAGE).toString()
				: langHeader;
		for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
			messages.append(messageSource.getMessage(constraintViolation.getMessage(),
					new Object[] { constraintViolation.getInvalidValue() },
					Locale.forLanguageTag(locale != null ? locale : Locale.ENGLISH.getLanguage()))).append(SEPARATOR);
		}
		String invalidField = messages.toString().substring(0, messages.lastIndexOf(SEPARATOR));
		logger.error("ConstraintViolationException: {}", invalidField);
		return createErrorResponse(HttpStatusCodes.CLIENT_ERROR_BAD_REQUEST, invalidField, 0);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response> handleConstraintViolation(MethodArgumentNotValidException ex) {
		String messages = getInvalidFieldMessage(ex.getBindingResult().getAllErrors());
		String invalidField = messages.substring(0, messages.lastIndexOf(SEPARATOR));
		logger.error("MethodArgumentNotValidException: {}", invalidField);
		return createErrorResponse(HttpStatusCodes.CLIENT_ERROR_BAD_REQUEST, invalidField, 0);
	}

	@ExceptionHandler(BindException.class)
	public ResponseEntity<Response> handleBindException(BindException ex) {
		String messages = getInvalidFieldMessage(ex.getAllErrors());
		String invalidField = messages.substring(0, messages.lastIndexOf(SEPARATOR));
		logger.error("BindException: {}", invalidField);
		return createErrorResponse(HttpStatusCodes.CLIENT_ERROR_BAD_REQUEST, invalidField, 0);
	}

	private String getInvalidFieldMessage(List<ObjectError> errorsList) {
		StringBuilder messages = new StringBuilder();
		String langHeader = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
		String locale = ValidationUtils.isEmpty(langHeader)
				? request.getAttribute(HttpHeaders.ACCEPT_LANGUAGE).toString()
				: langHeader;
		for (ObjectError error : errorsList) {
			Object rejectedValue = null;
			if (error instanceof FieldError) {
				rejectedValue = ((FieldError) error).getRejectedValue();
			}
			messages.append(messageSource.getMessage(error.getDefaultMessage(), new Object[] { rejectedValue },
					Locale.forLanguageTag(locale != null ? locale : Locale.ENGLISH.getLanguage()))).append(SEPARATOR);
		}
		return messages.toString();
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Response> handleBadCredentials(BadCredentialsException ex) {
		logger.error("BadCrdentialsExcetion: {}", ex.getMessage());
		return createResponse(HttpStatusCodes.CLIENT_ERROR_NOT_FOUND, UserMessages.INVALID_CREDENTIALS, null, 0);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<Response> handleAuthenticationException(AuthenticationException ex) {
		logger.error("Unauthoried user: {}", ex.getMessage());
		return createResponse(HttpStatusCodes.CLIENT_ERROR_UNAUTHORIZED, UserMessages.USER_UNAUTHORIZED, null, 0);
	}

}