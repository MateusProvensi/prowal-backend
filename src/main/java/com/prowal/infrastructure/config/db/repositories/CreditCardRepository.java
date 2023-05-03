package com.prowal.infrastructure.config.db.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prowal.infrastructure.config.db.schema.creditCard.CreditCardSchema;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardSchema, Long> {

	@Query("SELECT c FROM CreditCardSchema c INNER JOIN c.account a WHERE a.id = :accountId")
	List<CreditCardSchema> findByAccountId(@Param("accountId") Long accountId);

}
