package com.prowal.vos.v1.output.creditCard;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prowal.vos.v1.output.account.AccountVOOutput;

public class CreditCardVOOutput extends RepresentationModel<CreditCardVOOutput> implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "id")
	private Long key;

	private String description;

	private String finalNumbers;

	private String invoiceDate;

	private BigDecimal limitValue;

	private String dueDate;

	private AccountVOOutput account;

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

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

	public AccountVOOutput getAccount() {
		return account;
	}

	public void setAccount(AccountVOOutput account) {
		this.account = account;
	}
}
