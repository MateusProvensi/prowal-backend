package com.prowal.entities.creditCardTransaction.gateway;

import java.time.Instant;
import java.util.List;

import com.prowal.vos.v1.input.creditCardTransaction.CreditCardTransactionVOCreateInput;
import com.prowal.vos.v1.input.creditCardTransaction.CreditCardTransactionVOUpdateInput;
import com.prowal.vos.v1.output.creditCardTransaction.CreditCardTransactionVOOutput;


public interface CreditCardTransactionGateway {
	CreditCardTransactionVOOutput findById(Long id);

	List<CreditCardTransactionVOOutput> findByCreditCardIdAndPeriod(Long creditCardId, Instant dataInicial, Instant dataFinal);
	
	CreditCardTransactionVOOutput createCreditCardTransaction(CreditCardTransactionVOCreateInput creditCardTransactionVO);
	
	CreditCardTransactionVOOutput updateCreditCardTransaction(CreditCardTransactionVOUpdateInput creditCardTransactionVO);
	
	void deleteCreditCardTransaction(Long id);
}
