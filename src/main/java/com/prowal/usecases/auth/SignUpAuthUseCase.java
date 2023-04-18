package com.prowal.usecases.auth;

import org.springframework.stereotype.Service;

import com.prowal.entities.auth.gateway.AuthGateway;
import com.prowal.entities.user.gateway.UserGateway;
import com.prowal.infrastructure.config.exceptions.EntityExistsException;
import com.prowal.infrastructure.config.exceptions.RequiredObjectIsNullException;
import com.prowal.infrastructure.config.mapper.DozerMapper;
import com.prowal.vos.v1.input.auth.SignUpVOInput;
import com.prowal.vos.v1.input.user.UserVOInput;
import com.prowal.vos.v1.output.auth.TokenVO;
import com.prowal.vos.v1.output.user.UserVOOutput;

@Service
public class SignUpAuthUseCase {

	private AuthGateway authGateway;
	private UserGateway userGateway;

	public SignUpAuthUseCase(AuthGateway authGateway, UserGateway userGateway) {
		this.authGateway = authGateway;
		this.userGateway = userGateway;
	}

	public TokenVO signUp(SignUpVOInput data) {
		if (data == null) {
			throw new RequiredObjectIsNullException();
		}

		UserVOOutput userVO = userGateway.findByUsername(data.getUserName());

		if (userVO != null) {
			throw new EntityExistsException("Username '" + data.getUserName() + "' already exists!");
		}

		UserVOInput userVOInput = DozerMapper.parseObject(data, UserVOInput.class);

		String encondedPassword = authGateway.getEncondedPassword(data.getPassword());

		userVOInput.setPassword(encondedPassword);

		userVO = userGateway.create(userVOInput);

		return authGateway.signin(userVO.getUserName(), data.getPassword());
	}

}
