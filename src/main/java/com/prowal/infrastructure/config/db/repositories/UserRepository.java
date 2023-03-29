package com.prowal.infrastructure.config.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prowal.infrastructure.config.db.schema.UserSchema;

@Repository
public interface UserRepository extends JpaRepository<UserSchema, Long>{

	@Query("SELECT u FROM UserSchema u "
			+ "WHERE u.userName = :userName")
	UserSchema findByUserName(@Param("userName") String userName);
}
