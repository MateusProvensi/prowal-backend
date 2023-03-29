package com.prowal.usecases.auth;

import org.springframework.stereotype.Service;

import com.prowal.entities.auth.gateway.AuthGateway;
import com.prowal.entities.auth.model.vo.v1.TokenVO;

@Service
public class SignInAuthUseCase {
	private AuthGateway authGateway;

	public SignInAuthUseCase(AuthGateway authGateway) {
		this.authGateway = authGateway;
	}

	public TokenVO signin(String userName, String uncodedPassword) {
		return authGateway.signin(userName, uncodedPassword);
	}
}
