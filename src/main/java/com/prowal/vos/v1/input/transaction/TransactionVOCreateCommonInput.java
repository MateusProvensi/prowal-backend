package com.prowal.vos.v1.input.transaction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import com.prowal.infrastructure.config.db.schema.transaction.TypeTransaction;
import com.prowal.vos.v1.input.ids.AccountIdInput;
import com.prowal.vos.v1.input.ids.CategoryIdInput;
import com.prowal.vos.v1.input.ids.SubCategoryIdInput;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TransactionVOCreateCommonInput implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "Description should not be null")
	@NotBlank(message = "Description should not be blank")
	private String description;

	@NotNull(message = "Date should not be null")
	private Instant date;

	@NotNull(message = "Type should not be null")
	private TypeTransaction type;

	@NotNull(message = "Value should not be null")
	@Min(value = 0, message = "Value can not be less than 0")
	private BigDecimal value;

	@NotNull(message = "Primary Account should not be null")
	private AccountIdInput primaryAccount;

	@NotNull(message = "Category should not be null")
	private CategoryIdInput category;

	private SubCategoryIdInput subCategory;

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

	public AccountIdInput getPrimaryAccount() {
		return primaryAccount;
	}

	public void setPrimaryAccount(AccountIdInput primaryAccount) {
		this.primaryAccount = primaryAccount;
	}

	public CategoryIdInput getCategory() {
		return category;
	}

	public void setCategory(CategoryIdInput category) {
		this.category = category;
	}

	public SubCategoryIdInput getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategoryIdInput subCategory) {
		this.subCategory = subCategory;
	}
}
