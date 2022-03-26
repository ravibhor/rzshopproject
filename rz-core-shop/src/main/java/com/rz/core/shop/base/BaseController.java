package com.rz.core.shop.base;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.rz.core.shop.base.enums.DefaultMessages;
import com.rz.core.shop.base.enums.Messages;
import com.rz.core.shop.base.models.response.Response;
import com.rz.core.shop.base.models.response.ResponseBody;
import com.rz.core.shop.base.models.response.ResponseHeader;

@Component
@Validated
@SuppressWarnings(value = { "unused" })
public class BaseController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private HttpServletRequest request;

	/**
	 * 
	 * @param status
	 * @param version
	 * @return
	 */
	public ResponseEntity<Response> createResponse(int status, int version) {
		return createResponse(status, DefaultMessages.EMPTY_MESSAGE, null, version, 0, null);
	}

	/**
	 * 
	 * @param status
	 * @param message
	 * @param version
	 * @return
	 */
	public ResponseEntity<Response> createResponse(int status, Messages message, Object[] args, int version) {
		return createResponse(status, message, args, version, 0, null);
	}

	public ResponseEntity<Response> createResponse(int status, Messages message, Object[] args, String exception,
			int version) {
		return createResponse(status, message, args, exception, version, 0, null);
	}

	/**
	 * 
	 * @param status
	 * @param version
	 * @param responseBody
	 * @return
	 */
	public ResponseEntity<Response> createResponse(int status, int version, ResponseBody responseBody) {

		return createResponse(status, DefaultMessages.EMPTY_MESSAGE, null, version, 0, responseBody);

	}

	/**
	 * 
	 * @param status
	 * @param message
	 * @param version
	 * @param responseBody
	 * @return
	 */
	public ResponseEntity<Response> createResponse(int status, Messages message, Object[] args, int version,
			ResponseBody responseBody) {
		return createResponse(status, message, args, version, 0, responseBody);
	}

	/**
	 * 
	 * @param status
	 * @param message
	 * @param version
	 * @param errorCode
	 * @param responseBody
	 * @return
	 */
	public ResponseEntity<Response> createResponse(int status, Messages message, Object[] args, int version,
			int errorCode, ResponseBody responseBody) {

		return createResponse(status, message, args, null, version, errorCode, responseBody);
	}

	/**
	 * 
	 * @param status
	 * @param message
	 * @param version
	 * @param errorCode
	 * @param responseBody
	 * @return
	 */
	public ResponseEntity<Response> createResponse(int status, Messages message, Object[] args, String exception,
			int version, int errorCode, ResponseBody responseBody) {
		// The Headers.ACCEPT_LANG attribute is setting in the
		// AuthorizationRequestFilter.
		// We will always get Underscore separated language and country code.
		// String locale = request.getAttribute(Headers.ACCEPT_LANG).toString();
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setStatusCode(status);
		responseHeader.setVersion(version);
		responseHeader.setErrorCode(errorCode);
		// need to pass hypen(-) separated language and country code to forLanguageTag
		// method

		responseHeader.setMessage(
				messageSource.getMessage(message.getMessage(), args, Locale.ENGLISH));

		responseHeader.setException(exception);
		Response response = new Response();
		response.setHeader(responseHeader);
		response.setPayload(responseBody);
		return ResponseEntity.status(status).body(response);
	}

	/**
	 * Use this method only while sending MethodArgumentNotValidException message
	 * from global exception handler.
	 * 
	 * @param status
	 * @param errorMessage
	 * @param version
	 * @return
	 */
	public ResponseEntity<Response> createErrorResponse(int status, String errorMessage, int version) {
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setStatusCode(status);
		responseHeader.setVersion(version);
		responseHeader.setMessage(errorMessage);
		Response response = new Response();
		response.setHeader(responseHeader);
		return ResponseEntity.status(status).body(response);
	}
}