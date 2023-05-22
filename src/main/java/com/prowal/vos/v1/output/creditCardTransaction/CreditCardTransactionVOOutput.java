package com.prowal.vos.v1.output.creditCardTransaction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prowal.vos.v1.output.creditCard.CreditCardVOOutput;

public class CreditCardTransactionVOOutput extends RepresentationModel<CreditCardVOOutput> implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long key;

	private String description;

	private BigDecimal value;

	private Instant date;

	private Integer installment;

	private CreditCardVOOutput creditCard;

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

	public Integer getInstallment() {
		return installment;
	}

	public void setInstallment(Integer installment) {
		this.installment = installment;
	}

	public CreditCardVOOutput getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCardVOOutput creditCard) {
		this.creditCard = creditCard;
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
