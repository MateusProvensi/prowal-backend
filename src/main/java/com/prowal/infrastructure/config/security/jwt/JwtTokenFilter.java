package com.prowal.infrastructure.config.security.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.prowal.infrastructure.config.exceptions.InvalidJwtAuthenticationException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.minidev.json.JSONObject;

public class JwtTokenFilter extends GenericFilterBean {

	@Autowired
	private JwtTokenProvider tokenProvider;

	public JwtTokenFilter(JwtTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		try {			
			String token = tokenProvider.resolveToken(request);
			
			if (token != null && tokenProvider.validateToken(token)) {
				Authentication auth = tokenProvider.getAuthentication(token);
				
				if (auth != null) {
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			}
		} catch (InvalidJwtAuthenticationException e) {
	        JSONObject responseError = new JSONObject();
	        responseError.put("status", HttpStatus.UNAUTHORIZED.value());
	        responseError.put("message", e.getMessage());
		
	        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(responseError.toString());
            response.getWriter().flush();
            return;
		}

		chain.doFilter(req, res);
	}


}
