package com.prowal.usecases.auth;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prowal.entities.user.gateway.UserGateway;
import com.prowal.infrastructure.config.exceptions.InvalidJwtAuthenticationException;
import com.prowal.infrastructure.config.security.jwt.JwtTokenProvider;
import com.prowal.vos.v1.output.auth.TokenVO;
import com.prowal.vos.v1.output.user.UserVOOutput;

@Service
public class RefreshTokenUseCase {
	private UserGateway userGateway;
	private JwtTokenProvider tokenProvider;

	public RefreshTokenUseCase(UserGateway userGateway, JwtTokenProvider tokenProvider) {
		this.userGateway = userGateway;
		this.tokenProvider = tokenProvider;
	}

	public TokenVO refreshToken(String username, String refreshToken) {
		if (refreshToken == null) {
			throw new InvalidJwtAuthenticationException("The token has not been found");
		}
		
		UserVOOutput user = userGateway.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Username " + username + " not found!");
		}

		TokenVO tokenResponse = tokenProvider.refreshToken(refreshToken);

		if (tokenResponse == null) {
			throw new InvalidJwtAuthenticationException("The token is incorrect or expired");
		}

		return tokenResponse;
	}
}
