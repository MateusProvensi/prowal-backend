package com.prowal.vos.v1.input.creditCardTransaction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prowal.vos.v1.input.ids.CreditCardIdInput;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreditCardTransactionVOUpdateInput implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private Long id;

	@NotNull(message = "Description should not be null")
	@NotBlank(message = "Description should not be blank")
	private String description;

	@NotNull(message = "Date should not be null")
	private Instant date;

	@NotNull(message = "Value should not be null")
	@Min(value = 0, message = "Value can not be less than 0")
	private BigDecimal value;

	@JsonIgnore
	private CreditCardIdInput creditCard;

	@JsonIgnore
	private Integer installment;

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

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
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

	public Integer getInstallment() {
		return installment;
	}

	public void setInstallment(Integer installment) {
		this.installment = installment;
	}

	public CreditCardIdInput getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCardIdInput creditCard) {
		this.creditCard = creditCard;
	}
}
