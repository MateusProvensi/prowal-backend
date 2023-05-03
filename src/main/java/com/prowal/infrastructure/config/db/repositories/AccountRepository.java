package com.prowal.infrastructure.config.db.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prowal.infrastructure.config.db.schema.account.AccountSchema;

@Repository
public interface AccountRepository extends JpaRepository<AccountSchema, Long> {

	@Query("SELECT a FROM AccountSchema a INNER JOIN a.user u WHERE u.id = :userId")
	List<AccountSchema> findByUserId(@Param("userId") Long userId);
}
