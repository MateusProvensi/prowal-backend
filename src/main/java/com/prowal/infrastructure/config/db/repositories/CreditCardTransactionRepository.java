package com.prowal.infrastructure.config.db.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prowal.infrastructure.config.db.schema.creditCardTransaction.CreditCardTransactionSchema;

public interface CreditCardTransactionRepository extends JpaRepository<CreditCardTransactionSchema, Long> {

	@Query(value = "SELECT t FROM CreditCardTransactionSchema t " 
			+ "WHERE t.creditCard.id = :creditCardId "
			+ "AND t.date BETWEEN :dataInicial AND :dataFinal")
	List<CreditCardTransactionSchema> findByCreditCardWithPeriod(
			@Param("creditCardId") Long idCreditCard,
			@Param("dataInicial") Instant dataInicial,
			@Param("dataFinal") Instant dataFinal);
}
