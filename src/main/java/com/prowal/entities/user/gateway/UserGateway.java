package com.prowal.entities.user.gateway;

import com.prowal.vos.v1.input.user.UserVOInput;
import com.prowal.vos.v1.output.user.UserVOOutput;

public interface UserGateway {
	UserVOOutput create(UserVOInput userVO);
	UserVOOutput findByUsername(String username);
}
