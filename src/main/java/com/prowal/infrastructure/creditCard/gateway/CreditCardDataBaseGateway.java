package com.prowal.infrastructure.creditCard.gateway;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.prowal.entities.creditCard.gateway.CreditCardGateway;
import com.prowal.infrastructure.config.db.repositories.CreditCardRepository;
import com.prowal.infrastructure.config.db.schema.creditCard.CreditCardSchema;
import com.prowal.infrastructure.config.exceptions.EntityExistsException;
import com.prowal.infrastructure.config.mapper.ModelMapperMaping;
import com.prowal.vos.v1.input.creditCard.CreditCardVOCreateInput;
import com.prowal.vos.v1.input.creditCard.CreditCardVOUpdateInput;
import com.prowal.vos.v1.output.creditCard.CreditCardVOOutput;

@Component
public class CreditCardDataBaseGateway implements CreditCardGateway {

	private CreditCardRepository creditCardRepository;

	public CreditCardDataBaseGateway(CreditCardRepository creditCardRepository) {
		super();
		this.creditCardRepository = creditCardRepository;
	}

	@Override
	public CreditCardVOOutput findById(Long id) {
		Optional<CreditCardSchema> possibleCreditCard = creditCardRepository.findById(id);

		CreditCardVOOutput creditCardVO = possibleCreditCard
				.map(card -> ModelMapperMaping.parseObject(CreditCardSchema.class, CreditCardVOOutput.class))
				.orElseThrow(() -> new EntityExistsException("The credit card with ID = " + id + " does not exists."));

		return creditCardVO;
	}

	@Override
	public List<CreditCardVOOutput> findByAccountId(Long accountId) {
		List<CreditCardSchema> creditSchemas = creditCardRepository.findByAccountId(accountId);

		return ModelMapperMaping.parseListObjects(creditSchemas, CreditCardVOOutput.class);
	}

	@Override
	public void createCreditCard(CreditCardVOCreateInput accountVO) {
		CreditCardSchema creditToSave = ModelMapperMaping.parseObject(accountVO, CreditCardSchema.class);

		creditCardRepository.save(creditToSave);
	}

	@Override
	public void updateCreditCard(CreditCardVOUpdateInput accountVO) {
		CreditCardSchema creditToSave = ModelMapperMaping.parseObject(accountVO, CreditCardSchema.class);

		creditCardRepository.save(creditToSave);
	}

	@Override
	public void deleteCreditCard(Long id) {
		creditCardRepository.deleteById(id);
	}
}
