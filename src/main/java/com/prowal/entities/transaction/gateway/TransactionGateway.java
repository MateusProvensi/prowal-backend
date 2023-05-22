package com.prowal.entities.transaction.gateway;

import java.time.Instant;
import java.util.List;

import com.prowal.vos.v1.input.transaction.TransactionVOCreateCommonInput;
import com.prowal.vos.v1.input.transaction.TransactionVOCreateFromCreditCardInput;
import com.prowal.vos.v1.input.transaction.TransactionVOCreateTransferInput;
import com.prowal.vos.v1.input.transaction.TransactionVOUpdateInput;
import com.prowal.vos.v1.output.transaction.TransactionVoOutput;

public interface TransactionGateway {
	TransactionVoOutput findById(Long id);

	List<TransactionVoOutput> findByPeriod(Instant initialDate, Instant finalDate);
	
	void updateTransaction(TransactionVOUpdateInput transactionVO);
	
	void deleteTransaction(Long id);

	void createTransactionFromCreditCard(TransactionVOCreateFromCreditCardInput transactionVO);

	void createTransferTransaction(TransactionVOCreateTransferInput transactionVO);

	void createCommonTransaction(TransactionVOCreateCommonInput transactionVO);
}
