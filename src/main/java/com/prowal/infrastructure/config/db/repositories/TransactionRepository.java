package com.prowal.infrastructure.config.db.repositories;

import java.time.Instant;
import java.util.List;
import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prowal.infrastructure.config.db.schema.transaction.TransactionSchema;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionSchema, Long> {

	@Query(value = "SELECT t FROM TransactionSchema t " 
			+ "WHERE t.date BETWEEN :dataInicial AND :dataFinal "
			+ "AND (t.primaryAccount.id = :accountId OR t.secondaryAccount.id = :accountId)")
	List<TransactionSchema>
			findByPeriodAndAccount(@Param("dataInicial") Instant dataInicial, @Param("dataFinal") Instant dataFinal, @Param("accountId") Long accountId);
	
	@Query(value = "SELECT COALESCE(SUM(t.value), 0.0) FROM TransactionSchema t "
			+ "WHERE t.primaryAccount.id = :accountId "
			+ "AND t.date <= :currentDate "
			+ "AND t.type = TypeTransaction.EXPENSE")
	BigDecimal findExpensesValueByAccountIdUntilNow(@Param("accountId") Long accountId, @Param("currentDate") Instant currentDate);	

	@Query(value = "SELECT COALESCE(SUM(t.value), 0.0) FROM TransactionSchema t "
			+ "WHERE t.primaryAccount.id = :accountId "
			+ "AND t.date <= :currentDate "
			+ "AND t.type = TypeTransaction.INCOME")
	BigDecimal findIncomesValueByAccountIdUntilNow(@Param("accountId") Long accountId, @Param("currentDate") Instant currentDate);

	@Query(value = "SELECT COALESCE(SUM(t.value), 0.0) FROM TransactionSchema t "
			+ "WHERE t.type = TypeTransaction.TRANSFER "
			+ "AND t.primaryAccount.id = :accountId "
			+ "AND t.date <= :currentDate ")
	BigDecimal findTransfersValueThatThisAccountSentByAccountIdUntilNow(@Param("accountId") Long accountId, @Param("currentDate") Instant currentDate);
	
	@Query(value = "SELECT COALESCE(SUM(t.value), 0.0) FROM TransactionSchema t "
			+ "WHERE t.type = TypeTransaction.TRANSFER "
			+ "AND t.secondaryAccount.id = :accountId "
			+ "AND t.date <= :currentDate ")
	BigDecimal findTransfersValueThatThisAccountReceivedByAccountIdUntilNow(@Param("accountId") Long accountId, @Param("currentDate") Instant currentDate);
}
