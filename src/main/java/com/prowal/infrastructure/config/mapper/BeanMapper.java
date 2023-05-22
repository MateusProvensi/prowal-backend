package com.prowal.infrastructure.config.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.prowal.infrastructure.config.db.schema.account.AccountSchema;
import com.prowal.infrastructure.config.db.schema.category.CategorySchema;
import com.prowal.infrastructure.config.db.schema.creditCard.CreditCardSchema;
import com.prowal.infrastructure.config.db.schema.creditCardTransaction.CreditCardTransactionSchema;
import com.prowal.infrastructure.config.db.schema.subcategory.SubCategorySchema;
import com.prowal.infrastructure.config.db.schema.transaction.TransactionSchema;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.vos.v1.output.account.AccountVOOutput;
import com.prowal.vos.v1.output.category.CategoryVOOutput;
import com.prowal.vos.v1.output.creditCard.CreditCardVOOutput;
import com.prowal.vos.v1.output.creditCardTransaction.CreditCardTransactionVOOutput;
import com.prowal.vos.v1.output.subcategory.SubCategoryVOOutput;
import com.prowal.vos.v1.output.transaction.TransactionVoOutput;
import com.prowal.vos.v1.output.user.UserVOOutput;

@Configuration
public class BeanMapper {

	@Bean
	ModelMapper modelMapperGeneralConfig() {
		ModelMapper modelMapper = new ModelMapper();

		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		createTypesMapsToUser(modelMapper);
		createTypesMapsToAccount(modelMapper);
		createTypesMapsToCategory(modelMapper);
		createTypesMapsToSubCategory(modelMapper);
		createTypesMapsToCreditCard(modelMapper);
		createTypesMapsToCreditCardTransaction(modelMapper);
		createTypesMapsToTransaction(modelMapper);

		return modelMapper;
	}

	private void createTypesMapsToUser(ModelMapper modelMapper) {
		modelMapper
				.typeMap(UserSchema.class, UserVOOutput.class)
				.addMappings(map -> map.map(UserSchema::getId, UserVOOutput::setKey));
	}

	private void createTypesMapsToSubCategory(ModelMapper modelMapper) {
		modelMapper
				.typeMap(SubCategorySchema.class, SubCategoryVOOutput.class)
				.addMappings(map -> map.map(SubCategorySchema::getId, SubCategoryVOOutput::setKey));
	}

	private void createTypesMapsToCategory(ModelMapper modelMapper) {
		modelMapper
				.typeMap(CategorySchema.class, CategoryVOOutput.class)
				.addMappings(map -> map.map(CategorySchema::getId, CategoryVOOutput::setKey));
	}

	private void createTypesMapsToAccount(ModelMapper modelMapper) {
		modelMapper
				.typeMap(AccountSchema.class, AccountVOOutput.class)
				.addMappings(mapper -> mapper.map(AccountSchema::getId, AccountVOOutput::setKey));
	}

	private void createTypesMapsToCreditCard(ModelMapper modelMapper) {
		modelMapper
				.typeMap(CreditCardSchema.class, CreditCardVOOutput.class)
				.addMappings(mapper -> mapper.map(CreditCardSchema::getId, CreditCardVOOutput::setKey));
	}

	private void createTypesMapsToCreditCardTransaction(ModelMapper modelMapper) {
		modelMapper
				.typeMap(CreditCardTransactionSchema.class, CreditCardTransactionVOOutput.class)
				.addMappings(
						mapper -> mapper
								.map(CreditCardTransactionSchema::getId, CreditCardTransactionVOOutput::setKey));
	}

	private void createTypesMapsToTransaction(ModelMapper modelMapper) {
		modelMapper
				.typeMap(TransactionSchema.class, TransactionVoOutput.class)
				.addMappings(mapper -> mapper.map(TransactionSchema::getId, TransactionVoOutput::setKey));
	}
}
