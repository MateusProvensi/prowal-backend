package com.prowal.entities.user.gateway;

import com.prowal.entities.user.model.vo.v1.UserVO;

public interface UserGateway {
	UserVO create(UserVO userVO);
	UserVO findByUsername(String username);
}
