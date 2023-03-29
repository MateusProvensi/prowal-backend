package com.prowal.entities.auth.gateway;

import com.prowal.entities.auth.model.vo.v1.TokenVO;

public interface AuthGateway {
	String getEncondedPassword(String password);

	TokenVO signin(String userName, String uncodedPassword);
}
