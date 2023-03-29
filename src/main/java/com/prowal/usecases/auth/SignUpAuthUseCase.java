package com.prowal.usecases.auth;

import org.springframework.stereotype.Service;

import com.prowal.entities.auth.gateway.AuthGateway;
import com.prowal.entities.auth.model.vo.v1.SignUpVO;
import com.prowal.entities.auth.model.vo.v1.TokenVO;
import com.prowal.entities.user.gateway.UserGateway;
import com.prowal.entities.user.model.vo.v1.UserVO;
import com.prowal.infrastructure.config.exceptions.EntityExistsException;
import com.prowal.infrastructure.config.exceptions.RequiredObjectIsNullException;
import com.prowal.infrastructure.config.mapper.DozerMapper;

@Service
public class SignUpAuthUseCase {

	private AuthGateway authGateway;
	private UserGateway userGateway;

	public SignUpAuthUseCase(AuthGateway authGateway, UserGateway userGateway) {
		this.authGateway = authGateway;
		this.userGateway = userGateway;
	}

	public TokenVO signUp(SignUpVO data) {
		if (data == null) {
			throw new RequiredObjectIsNullException();
		}

		data.setId(null);

		UserVO userVo = userGateway.findByUsername(data.getUserName());

		if (userVo != null) {
			throw new EntityExistsException("Username '" + data.getUserName() + "' already exists!");
		}

		UserVO userVO = DozerMapper.parseObject(data, UserVO.class);

		String encondedPassword = authGateway.getEncondedPassword(data.getPassword());

		userVO.setPassword(encondedPassword);

		userVO = userGateway.create(userVO);

		SignUpVO signUpVO = DozerMapper.parseObject(userVO, SignUpVO.class);

		return authGateway.signin(signUpVO.getUserName(), data.getPassword());
	}

}
