package com.prowal.infrastructure.user.gateway;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.prowal.entities.user.gateway.UserGateway;
import com.prowal.infrastructure.config.db.repositories.UserRepository;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.infrastructure.config.mapper.DozerMapper;
import com.prowal.vos.v1.input.user.UserVOInput;
import com.prowal.vos.v1.output.user.UserVOOutput;

@Component
public class UserDatabaseGateway implements UserGateway, UserDetailsService {

	private final UserRepository userRepository;
	
	public UserDatabaseGateway(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserVOOutput create(UserVOInput userVO) {
		UserSchema entity = DozerMapper.parseObject(userVO, UserSchema.class);

		UserSchema persistedEntity = userRepository.save(entity);
		
		UserVOOutput vo = DozerMapper.parseObject(persistedEntity, UserVOOutput.class);

		// vo.add(linkTo(methodOn(UserController.class).findById(vo.getKey())).withSelfRel());
		
		return vo;
	}

	@Override
	public UserVOOutput findByUsername(String username) {
		UserSchema user = userRepository.findByUserName(username);

		return DozerMapper.parseObject(user, UserVOOutput.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserSchema user = userRepository.findByUserName(username);

		if (user == null) {
			throw new UsernameNotFoundException("Username " + username + " not found!");
		}

		return user;
	}

}
