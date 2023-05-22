package com.prowal.vos.v1.output.transaction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;
import com.prowal.infrastructure.config.db.schema.transaction.TypeTransaction;
import com.prowal.vos.v1.output.account.AccountVOOutput;
import com.prowal.vos.v1.output.category.CategoryVOOutput;
import com.prowal.vos.v1.output.creditCardTransaction.CreditCardTransactionVOOutput;
import com.prowal.vos.v1.output.subcategory.SubCategoryVOOutput;

public class TransactionVoOutput extends RepresentationModel<TransactionVoOutput> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Mapping(value = "id")
	@JsonProperty(value = "id")
	private Long key;

	private String description;

	private Instant date;

	private TypeTransaction type;

	private BigDecimal value;

	private AccountVOOutput primaryAccount;

	private AccountVOOutput secondaryAccount;

	private CategoryVOOutput category;

	private SubCategoryVOOutput subCategory;

	private CreditCardTransactionVOOutput creditCardTransaction;

	private Instant createdAt;

	private Instant updatedAt;

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public TypeTransaction getType() {
		return type;
	}

	public void setType(TypeTransaction type) {
		this.type = type;
	}

	public AccountVOOutput getPrimaryAccount() {
		return primaryAccount;
	}

	public void setPrimaryAccount(AccountVOOutput primaryAccount) {
		this.primaryAccount = primaryAccount;
	}

	public AccountVOOutput getSecondaryAccount() {
		return secondaryAccount;
	}

	public void setSecondaryAccount(AccountVOOutput secondaryAccount) {
		this.secondaryAccount = secondaryAccount;
	}

	public CategoryVOOutput getCategory() {
		return category;
	}

	public void setCategory(CategoryVOOutput category) {
		this.category = category;
	}

	public SubCategoryVOOutput getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategoryVOOutput subCategory) {
		this.subCategory = subCategory;
	}

	public CreditCardTransactionVOOutput getCreditCardTransaction() {
		return creditCardTransaction;
	}

	public void setCreditCardTransaction(CreditCardTransactionVOOutput creditCardTransaction) {
		this.creditCardTransaction = creditCardTransaction;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}
}
