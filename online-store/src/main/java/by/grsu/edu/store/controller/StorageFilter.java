package by.grsu.edu.store.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StorageFilter implements Filter {
	private static final String SIGNIN_URL = "/online-store/users/signin";
	private static final String SIGNUP_URL = "/online-store/users/signup";

	private static final String TOKEN = "token";

	public StorageFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse httpResponse = ((HttpServletResponse) response);
		httpResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
		httpResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
		httpResponse.setHeader("Access-Control-Allow-Headers",
				"Access-Control-Allow-Origin,Access-Control-Allow-Credentials,token,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers");

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		if (httpRequest.getRequestURI().equals(SIGNIN_URL) || httpRequest.getRequestURI().equals(SIGNUP_URL)) {
			chain.doFilter(request, response);
			return;
		}
		String token = httpRequest.getHeader(TOKEN);
		if (token == null || (!TokenMap.getTokenMap().containsToken(token))) {
			return;
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
