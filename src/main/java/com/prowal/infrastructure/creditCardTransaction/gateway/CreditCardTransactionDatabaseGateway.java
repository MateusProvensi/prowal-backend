package com.prowal.infrastructure.creditCardTransaction.gateway;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.prowal.entities.creditCardTransaction.gateway.CreditCardTransactionGateway;
import com.prowal.infrastructure.config.db.repositories.CreditCardTransactionRepository;
import com.prowal.infrastructure.config.db.schema.creditCardTransaction.CreditCardTransactionSchema;
import com.prowal.infrastructure.config.exceptions.EntityExistsException;
import com.prowal.infrastructure.config.mapper.ModelMapperMaping;
import com.prowal.vos.v1.input.creditCardTransaction.CreditCardTransactionVOCreateInput;
import com.prowal.vos.v1.input.creditCardTransaction.CreditCardTransactionVOUpdateInput;
import com.prowal.vos.v1.output.creditCardTransaction.CreditCardTransactionVOOutput;

import core.FunctionsDateUtils;

@Component
public class CreditCardTransactionDatabaseGateway implements CreditCardTransactionGateway {

	private CreditCardTransactionRepository creditCardTransactionRepository;

	public CreditCardTransactionDatabaseGateway(CreditCardTransactionRepository creditCardTransactionRepository) {
		super();
		this.creditCardTransactionRepository = creditCardTransactionRepository;
	}

	@Override
	public CreditCardTransactionVOOutput findById(Long id) {
		Optional<CreditCardTransactionSchema> possibleCreditCardTransaction = creditCardTransactionRepository
				.findById(id);

		CreditCardTransactionVOOutput creditCardTransactionVO = possibleCreditCardTransaction
				.map(transaction -> ModelMapperMaping.parseObject(transaction, CreditCardTransactionVOOutput.class))
				.orElseThrow(
						() -> new EntityExistsException(
								"The credit card transaction with ID = " + id + " does not exists."));

		return creditCardTransactionVO;
	}

	@Override
	public
			List<CreditCardTransactionVOOutput>
			findByCreditCardIdAndPeriod(Long creditCardId, Instant dataInicial, Instant dataFinal) {
		List<CreditCardTransactionSchema> transactions = creditCardTransactionRepository
				.findByCreditCardWithPeriod(creditCardId, dataInicial, dataFinal);

		if (transactions.isEmpty()) {
			throw new EntityExistsException(
					"Do not exists credit card transactions with this credit card or in this period.");
		}

		return ModelMapperMaping.parseListObjects(transactions, CreditCardTransactionVOOutput.class);
	}

	@Override
	public CreditCardTransactionVOOutput createCreditCardTransaction(
			CreditCardTransactionVOCreateInput creditCardTransactionVO) {
		CreditCardTransactionSchema transactionToSave = ModelMapperMaping
				.parseObject(creditCardTransactionVO, CreditCardTransactionSchema.class);

		Instant currentTime = FunctionsDateUtils.currentInstantDateTime();

		transactionToSave.setCreatedAt(currentTime);
		transactionToSave.setUpdatedAt(currentTime);

		CreditCardTransactionSchema creditCardTransaction = creditCardTransactionRepository.save(transactionToSave);

		return ModelMapperMaping.parseObject(creditCardTransaction, CreditCardTransactionVOOutput.class);
	}

	@Override
	public CreditCardTransactionVOOutput updateCreditCardTransaction(
			CreditCardTransactionVOUpdateInput creditCardTransactionVO) {
		CreditCardTransactionSchema transactionToUpdate = ModelMapperMaping
				.parseObject(creditCardTransactionVO, CreditCardTransactionSchema.class);

		Instant currentTime = FunctionsDateUtils.currentInstantDateTime();

		transactionToUpdate.setUpdatedAt(currentTime);

		CreditCardTransactionSchema creditCardTransaction = creditCardTransactionRepository.save(transactionToUpdate);

		return ModelMapperMaping.parseObject(creditCardTransaction, CreditCardTransactionVOOutput.class);
	}

	@Override
	public void deleteCreditCardTransaction(Long id) {
		creditCardTransactionRepository.deleteById(id);
	}
}
