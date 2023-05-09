package com.prowal.vos.v1.input.creditCard;

import java.io.Serializable;
import java.math.BigDecimal;

import com.prowal.vos.v1.input.ids.AccountIdInput;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreditCardVOCreateInput implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "Description should not be null")
	@NotBlank(message = "Description should not be blank")
	private String description;

	@NotNull(message = "Final numbers should not be null")
	@NotBlank(message = "Final numbers should not be blank")
	@Size(max = 4, min = 4, message = "The length need to be 4")
	private String finalNumbers;

	@NotNull(message = "Invoice Date should not be null")
	@NotBlank(message = "Invoice Date should not be blank")
	private String invoiceDate;

	@NotNull(message = "Limit Value should not be null")
	private BigDecimal limitValue;

	@NotNull(message = "Due Date should not be null")
	@NotBlank(message = "Due Date should not be blank")
	private String dueDate;

	@NotNull(message = "Account should not be null")
	private AccountIdInput account;

	public BigDecimal getLimitValue() {
		return limitValue;
	}

	public void setLimitValue(BigDecimal limitValue) {
		this.limitValue = limitValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFinalNumbers() {
		return finalNumbers;
	}

	public void setFinalNumbers(String finalNumbers) {
		this.finalNumbers = finalNumbers;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public AccountIdInput getAccount() {
		return account;
	}

	public void setAccount(AccountIdInput account) {
		this.account = account;
	}
}
