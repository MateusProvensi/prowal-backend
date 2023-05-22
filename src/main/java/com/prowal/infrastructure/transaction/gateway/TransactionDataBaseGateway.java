package com.prowal.infrastructure.transaction.gateway;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.prowal.entities.transaction.gateway.TransactionGateway;
import com.prowal.infrastructure.config.db.repositories.TransactionRepository;
import com.prowal.infrastructure.config.db.schema.transaction.TransactionSchema;
import com.prowal.infrastructure.config.exceptions.EntityExistsException;
import com.prowal.infrastructure.config.mapper.ModelMapperMaping;
import com.prowal.vos.v1.input.transaction.TransactionVOCreateCommonInput;
import com.prowal.vos.v1.input.transaction.TransactionVOCreateFromCreditCardInput;
import com.prowal.vos.v1.input.transaction.TransactionVOCreateTransferInput;
import com.prowal.vos.v1.input.transaction.TransactionVOUpdateInput;
import com.prowal.vos.v1.output.transaction.TransactionVoOutput;

import core.FunctionsDateUtils;

@Component
public class TransactionDataBaseGateway implements TransactionGateway {

	private TransactionRepository transactionRepository;

	public TransactionDataBaseGateway(TransactionRepository transactionRepository) {
		super();
		this.transactionRepository = transactionRepository;
	}

	@Override
	public TransactionVoOutput findById(Long id) {
		Optional<TransactionSchema> possibleTransaction = transactionRepository.findById(id);

		TransactionVoOutput transactionVO = possibleTransaction
				.map(transaction -> ModelMapperMaping.parseObject(transaction, TransactionVoOutput.class))
				.orElseThrow(() -> new EntityExistsException("The transaction with ID = " + id + " does not exists."));

		return transactionVO;
	}

	@Override
	public List<TransactionVoOutput> findByPeriod(Instant initialDate, Instant finalDate) {
		List<TransactionSchema> transactions = transactionRepository.findByPeriod(initialDate, finalDate);

		if (transactions.isEmpty()) {
			throw new EntityExistsException("Do not exists transactions with this credit card or in this period.");
		}

		return ModelMapperMaping.parseListObjects(transactions, TransactionVoOutput.class);
	}

	@Override
	public void createCommonTransaction(TransactionVOCreateCommonInput transactionVO) {
		TransactionSchema transactionToSave = ModelMapperMaping.parseObject(transactionVO, TransactionSchema.class);

		Instant currentTime = FunctionsDateUtils.currentInstantDateTime();

		transactionToSave.setCreatedAt(currentTime);
		transactionToSave.setUpdatedAt(currentTime);

		transactionRepository.save(transactionToSave);
	}

	@Override
	public void createTransactionFromCreditCard(TransactionVOCreateFromCreditCardInput transactionVO) {
		TransactionSchema transactionToSave = ModelMapperMaping.parseObject(transactionVO, TransactionSchema.class);

		Instant currentTime = FunctionsDateUtils.currentInstantDateTime();

		transactionToSave.setCreatedAt(currentTime);
		transactionToSave.setUpdatedAt(currentTime);

		transactionRepository.save(transactionToSave);
	}

	@Override
	public void createTransferTransaction(TransactionVOCreateTransferInput transactionVO) {
		TransactionSchema transactionToSave = ModelMapperMaping.parseObject(transactionVO, TransactionSchema.class);

		Instant currentTime = FunctionsDateUtils.currentInstantDateTime();

		transactionToSave.setCreatedAt(currentTime);
		transactionToSave.setUpdatedAt(currentTime);

		transactionRepository.save(transactionToSave);
	}

	@Override
	public void updateTransaction(TransactionVOUpdateInput transactionVO) {
		TransactionSchema transactionToUpdate = ModelMapperMaping.parseObject(transactionVO, TransactionSchema.class);

		Instant currentTime = FunctionsDateUtils.currentInstantDateTime();

		transactionToUpdate.setUpdatedAt(currentTime);

		transactionRepository.save(transactionToUpdate);
	}

	@Override
	public void deleteTransaction(Long id) {
		transactionRepository.deleteById(id);
	}
}
