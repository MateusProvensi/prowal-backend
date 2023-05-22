package com.prowal.infrastructure.config.db.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prowal.infrastructure.config.db.schema.transaction.TransactionSchema;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionSchema, Long> {

	@Query(value = "SELECT t FROM TransactionSchema t " 
			+ "WHERE t.date BETWEEN :dataInicial AND :dataFinal")
	List<TransactionSchema>
			findByPeriod(@Param("dataInicial") Instant dataInicial, @Param("dataFinal") Instant dataFinal);
}
