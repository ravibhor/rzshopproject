package com.rz.core.shop.base.constants;


public class HttpStatusCodes {

	/*
	 * 2xx - Success This class of status code indicates that the client's
	 * request was successfully received, understood, and accepted.
	 */
	public static final int SUCCESS_OK = 200;
	public static final int SUCCESS_CREATED = 201;
	public static final int SUCCESS_NO_CONTENT = 204;

	/*
	 * 3xx - Redirection This class of status code indicates that further action
	 * needs to be taken by the user agent in order to fulfill the request.
	 */
	public static final int REDIRECTION_NOT_MODIFIED = 304;

	/*
	 * 4xx - Client Error This class of status code is intended for cases in
	 * which the client seems to have erred.
	 */
	public static final int CLIENT_ERROR_BAD_REQUEST = 400;
	public static final int CLIENT_ERROR_UNAUTHORIZED = 401;
	public static final int CLIENT_ERROR_FORBIDDEN = 403;
	public static final int CLIENT_ERROR_NOT_FOUND = 404;
	public static final int CLIENT_ERROR_METHOD_NOT_ALLOWED = 405;
	public static final int CLIENT_ERROR_CONFLICT = 409;

	public static final int EMAIL_ID_ALREADY_EXISTS = 407;
	public static final int USER_ERROR_UNAUTHORIZED = 401;
	public static final int USER_ALREADY_EXISTS = 406;
	public static final int ALREADY_EXISTS = 406;
	public static final int GOOGLE_CPTCHA_VERFICATION_FAILED = 408;

	public static final int PROFILE_INCOMPLETE = 410;
	public static final int INSUFFICIENT_AMOUNT = 411;
	public static final int DATE_EXPIRED = 412;

	public static final int ONLINE_TRANSACTION_CANCEL = 413;
	public static final int TRANSACTION_FAIL = 412;
	public static final int EXPECTATION_FAILED = 417;

	public static final int ASSESSMENT_NOT_EXISTS = 600;
	public static final int DUPLICATE_RECORD_EXISTS = 601;
	public static final int ASSESSMENT_COMPLETE_EXISTS = 602;

	public static final int NOT_UPLOADED_BY_STAKEHOLDER = 701;
	
	public static final int CLIENT_PRECONDITION_FAILED  = 412;

	/*
	 * 5xx - Server Error This class of status code indicates cases in which the
	 * server is aware that it has erred or is incapable of performing the
	 * request.
	 */
	public static final int SERVER_INTERNAL_SERVER_ERROR = 500;

	private HttpStatusCodes() {

	}

}