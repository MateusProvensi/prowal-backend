package com.prowal.entities.auth.gateway;

import com.prowal.vos.v1.output.auth.TokenVO;

public interface AuthGateway {
	String getEncondedPassword(String password);

	TokenVO signin(String userName, String uncodedPassword);
}
