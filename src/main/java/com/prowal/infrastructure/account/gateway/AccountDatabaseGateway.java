package com.prowal.infrastructure.account.gateway;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.prowal.entities.account.gateway.AccountGateway;
import com.prowal.infrastructure.config.db.repositories.AccountRepository;
import com.prowal.infrastructure.config.db.schema.account.AccountSchema;
import com.prowal.infrastructure.config.exceptions.EntityExistsException;
import com.prowal.infrastructure.config.mapper.ModelMapperMaping;
import com.prowal.vos.v1.input.account.AccountVOCreateInput;
import com.prowal.vos.v1.input.account.AccountVOUpdateInput;
import com.prowal.vos.v1.output.account.AccountVOOutput;

@Component
public class AccountDatabaseGateway implements AccountGateway {

	private final AccountRepository accountRepository;
	

	public AccountDatabaseGateway(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountVOOutput findById(Long id) {
		Optional<AccountSchema> account = accountRepository.findById(id);
		
		AccountVOOutput accountVOOutput = account
				.map(currentAccount -> ModelMapperMaping.parseObject(currentAccount, AccountVOOutput.class))
				.orElseThrow(() -> new EntityExistsException("The account with ID = " + id + " does not exists."));

		return accountVOOutput;
	}

	@Override
	public List<AccountVOOutput> findByUserId(Long userId) {
		List<AccountSchema> accounts = accountRepository.findByUserId(userId);

		return ModelMapperMaping.parseListObjects(accounts, AccountVOOutput.class);
	}

	@Override
	public void createAccount(AccountVOCreateInput accountVO) {
		AccountSchema accountToInsert = ModelMapperMaping.parseObject(accountVO, AccountSchema.class);

		accountRepository.save(accountToInsert);
	}

	@Override
	public void updateAccount(AccountVOUpdateInput accountVO) {
		AccountSchema accountToInsert = ModelMapperMaping.parseObject(accountVO, AccountSchema.class);

		accountRepository.save(accountToInsert);
	}

	@Override
	public void deleteAccount(Long id) {
		accountRepository.deleteById(id);
	}
}
