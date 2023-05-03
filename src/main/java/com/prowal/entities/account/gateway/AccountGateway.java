package com.prowal.entities.account.gateway;

import java.util.List;

import com.prowal.vos.v1.input.account.AccountVOCreateInput;
import com.prowal.vos.v1.input.account.AccountVOUpdateInput;
import com.prowal.vos.v1.output.account.AccountVOOutput;


public interface AccountGateway {
	AccountVOOutput findById(Long id);

	List<AccountVOOutput> findByUserId(Long userId);
	
	void createAccount(AccountVOCreateInput accountVO);
	
	void updateAccount(AccountVOUpdateInput accountVO);
	
	void deleteAccount(Long id);
}
