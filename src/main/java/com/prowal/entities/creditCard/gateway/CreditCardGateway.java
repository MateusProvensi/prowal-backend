package com.prowal.entities.creditCard.gateway;

import java.util.List;

import com.prowal.vos.v1.input.creditCard.CreditCardVOCreateInput;
import com.prowal.vos.v1.input.creditCard.CreditCardVOUpdateInput;
import com.prowal.vos.v1.output.creditCard.CreditCardVOOutput;

public interface CreditCardGateway {
	CreditCardVOOutput findById(Long id);

	List<CreditCardVOOutput> findByAccountId(Long userId);
	
	void createCreditCard(CreditCardVOCreateInput accountVO);
	
	void updateCreditCard(CreditCardVOUpdateInput accountVO);
	
	void deleteCreditCard(Long id);
}
