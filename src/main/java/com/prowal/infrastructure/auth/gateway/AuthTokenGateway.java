package com.prowal.infrastructure.auth.gateway;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.prowal.entities.auth.gateway.AuthGateway;
import com.prowal.entities.user.gateway.UserGateway;
import com.prowal.infrastructure.config.security.jwt.JwtTokenProvider;
import com.prowal.vos.v1.output.auth.TokenVO;
import com.prowal.vos.v1.output.user.UserVOOutput;

@Component
public class AuthTokenGateway implements AuthGateway {

	private JwtTokenProvider tokenProvider;
	private AuthenticationManager authenticationManager;
	private UserGateway userGateway;
	private PasswordEncoder passwordEncoder;

	public AuthTokenGateway(
			JwtTokenProvider tokenProvider,
			AuthenticationManager authenticationManager,
			UserGateway userGateway,
			PasswordEncoder passwordEncoder) {
		this.tokenProvider = tokenProvider;
		this.authenticationManager = authenticationManager;
		this.userGateway = userGateway;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public TokenVO signin(String userName, String uncodedPassword) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, uncodedPassword));

			UserVOOutput userVo = userGateway.findByUsername(userName);

			if (userVo == null) {
				throw new UsernameNotFoundException("Username " + userName + " not found!");
			}

			return tokenProvider.createAccessToken(userName, new ArrayList<>()); 
		} catch (Exception e) {
			throw new BadCredentialsException("Invalid Username/Password Supplied!");
		}
	}

	@Override
	public String getEncondedPassword(String password) {
		return passwordEncoder.encode(password);
	}
}
