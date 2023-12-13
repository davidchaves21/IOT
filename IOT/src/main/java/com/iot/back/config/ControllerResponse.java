package com.iot.back.config;

public class ControllerResponse {
	public static final String SECURITY_REQUERIMENT = "Bearer Authentication";
	public static final String CONTENT = "application/json";

	public class StatusCode {
		public static final String OK = "200";
		public static final String CREATE = "201";
		public static final String NON_AUTHORITATIVE_INFORMATION = "203";
		public static final String NO_CONTENT = "204";
		public static final String FOUND = "302";
		public static final String BAD_REQUEST = "400";
		public static final String UNAUTHORIZED = "401";
		public static final String PAYMENT_REQUIRED = "402";
		public static final String FORBIDDEN = "403";
		public static final String NOT_FOUND = "404";
	}

	public class UserResponseDescription {
		public static final String UNAUTHORIZED = "Expired or invalid token for the request";
		public static final String NOT_FOUND = "User username not found or User not found, ID:  id or User not found for the given CPF";

		public static final String REGISTER = "User successfully created";

		public static final String LOGIN = "Returns in the Header and Body of the request a Bearer Token";

		public static final String RECOVER = "Recovery attempt successful, e-mail sent with a link to /resetPassword containing the token for authentication in your URI. ";
	
		public static final String RESET = "Password reset success and consume token acess";

		public static final String WHOAMI = "The request was successful and the user's information was presented in DTO format";

		public static final String USERS = "The request was successful and the user's information was presented in DTO format. You will be returned a list containing this information";

		public static final String USERS_ID = "Returns all the information of the user searched for by his id.";

		public static final String CONTAIN = "Returns a list of only the initial registered username with the search parameters. The request was successful and the user's information was presented in DTO format";

		public static final String USERNAME = "Return the registered information of the searched username. The request was successful and the user's information was presented in DTO format";


		public static final String MANAGEMENT_COMPANIES = "Success in requesting information.The response is represented using the paging parameters \"page\" and \"size\". If no pagination values are entered, the system defaults to returning page 0 with size 10. Response paginated information according to the following schema:";

		public static final String MANAGEMENT_DETAIL = "Success in requesting information. Response paginated information according to the following schema:";
		
	}
	public class HeaderName {
		public static final String ACCEPT = "Accept";
		public static final String ACCEPT_CHARSET = "Accept-Charset";
		public static final String ACCEPT_ENCODING = "Accept-Encoding";
		public static final String ACCEPT_LANGUAGE = "Accept-Language";
		public static final String AUTHORIZATION = "Authorization";
		public static final String CACHE_CONTROL = "Cache-Control";
		public static final String CONTENT_LENGTH = "Content-Length";
		public static final String CONTENT_TYPE = "Content-Type";
		public static final String EXPIRES = "Expires";
		public static final String HOST = "Host";
		public static final String LOCATION = "Location";
		public static final String PRAGMA = "Pragma";
		public static final String PROXY_AUTHORIZATION = "Proxy-Authorization";
		public static final String RANGE = "Range";
		public static final String REFERER = "Referer";
		public static final String SERVER = "Server";
		public static final String TRANSFER_ENCODING = "Transfer-Encoding";
	}

	public class HeaderDescription {
		public static final String COMPANY_CREATE = "URL of created company";
		public static final String REGISTER = "URL of created user";
		public static final String IPO = "URL of created ipo";

	}

	public class SchemaType {
		public static final String TOPIC = " \n - ";
		public static final String STRING = "string";
		public static final String URL = "url";
		public static final String POSSIBLE_ERRO = "Possible errors: " + TOPIC;

	}

	public class SchemaExemple {
		public static final String REGISTER = "/users/{user_id}";		
		public static final String DATE_FORMAT = "Pattern(ISO-8601): yyyy-MM-dd'T'HH:mm:ss.SSSXXX ex: 2023-05-21T19:18:50.181-03:00";

	}

	public class Content {
		public static final String JSON = "application/json";
		public static final String TEXT_PLAIN = "text/plain";
		public static final String EMAIL = "{\"uri\":\"http://localhost:8100/resetPassword/{bearer_token}\"}";
	}

	public class RegisterExceptionCode {
		public static final String NAME = "1001 - Invalid name";
		public static final String EMAIL = "1002 - Invalid email";
		public static final String USERNAME = "1003 - Invalid username";
		public static final String PASSWORD = "1004 - Invalid password";
		public static final String UNAUTHORIZED_REQUEST_LOGIN = "1005 - Invalid Username or Password for login";


	}

	public class UserExceptionCode {
		public static final String AUTHORIZATION_HEADER = "2001 - authorizationHeader invalid or null";
		public static final String PRODUTO_IN_CART = "2002 - PRODUCT IN CART";
		public static final String STOCK = "2003 - PRODUCT STOCK OVER";
		public static final String ZERO = "2004 - USE REMOVE";
		public static final String PRODUTO_NO_CART = "2005 - PRODUCT NÃO ESTA NO CART";


	
	}
	

	public class TokenExceptionCode {
		public static final String MASTER = "7001 - No MASTER Token";
		public static final String INVALID = "7002 - Invalid token, could not find session user";
		public static final String EXPIRED = "7003 - This token has already expired or been used or is not valid for this request";

		public static final String TOKEN_RESET = "7004 - This token has already been used or is not valid for this request";
		public static final String DEACTIVATE_FAILURE = "7005 - Failed to deactivate token ";
		public static final String TOKEN_FOR_RESET = "7006 - Unauthorized token for this request, so can be used for password reset ";

		public static final String UNAUTHORIZED_RESET = SchemaType.POSSIBLE_ERRO + TokenExceptionCode.INVALID
				+ SchemaType.TOPIC + TokenExceptionCode.EXPIRED + SchemaType.TOPIC + TokenExceptionCode.TOKEN_RESET
				+ SchemaType.TOPIC + TokenExceptionCode.DEACTIVATE_FAILURE;

		public static final String UNATHORIZED_USER = SchemaType.POSSIBLE_ERRO + TokenExceptionCode.INVALID
				+ SchemaType.TOPIC + TokenExceptionCode.EXPIRED;

		public static final String UNAUTHORIZED_RESPONSE = SchemaType.POSSIBLE_ERRO + TokenExceptionCode.MASTER
				+ SchemaType.TOPIC + TokenExceptionCode.INVALID + SchemaType.TOPIC + TokenExceptionCode.EXPIRED;

		public static final String UNAUTHORIZED = SchemaType.POSSIBLE_ERRO + TokenExceptionCode.INVALID
				+ SchemaType.TOPIC + TokenExceptionCode.EXPIRED;

	}


	public class ResourceNotFoundExceptionCode {
		public static final String USER_ID = "3001 - Product ID Not Found: ";

		public static final String PRODUCT_ID = "3002 - Product ID Not Found: ";
		

	}
	
	
	

}