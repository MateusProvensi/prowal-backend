package com.prowal.vos.v1.input.transaction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prowal.infrastructure.config.db.schema.transaction.TypeTransaction;
import com.prowal.vos.v1.output.account.AccountVOOutput;
import com.prowal.vos.v1.output.category.CategoryVOOutput;
import com.prowal.vos.v1.output.creditCardTransaction.CreditCardTransactionVOOutput;
import com.prowal.vos.v1.output.subcategory.SubCategoryVOOutput;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TransactionVOUpdateInput implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private Long id;

	@NotNull(message = "Description should not be null")
	@NotBlank(message = "Description should not be blank")
	private String description;

	@NotNull(message = "Date should not be null")
	private Instant date;

	@NotNull(message = "Type should not be null")
	@NotBlank(message = "Type should not be blank")
	private TypeTransaction type;

	@NotNull(message = "Value should not be null")
	@Min(value = 0, message = "Value can not be less than 0")
	private BigDecimal value;

	@NotNull(message = "Primary Account should not be null")
	private AccountVOOutput primaryAccount;

	@NotNull(message = "Secondary Account should not be null")
	private AccountVOOutput secondaryAccount;

	@NotNull(message = "Category should not be null")
	private CategoryVOOutput category;

	@NotNull(message = "SubCategory should not be null")
	private SubCategoryVOOutput subCategory;

	@NotNull(message = "Credit Card Transaction should not be null")
	private CreditCardTransactionVOOutput creditCardTransaction;

	@JsonIgnore
	private Instant createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
}
