package com.prowal.vos.v1.input.creditCardTransaction;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prowal.vos.v1.input.ids.CreditCardIdInput;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreditCardTransactionVOCreateInput implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private Long id;

	@NotNull(message = "Description should not be null")
	@NotBlank(message = "Description should not be blank")
	private String description;

	@NotNull(message = "Date should not be null")
	private Instant date;

	@NotNull(message = "Credit Card should not be null")
	private CreditCardIdInput creditCard;

	@NotNull(message = "Quantity of installments should not be null")
	private Integer quantityOfInstallments;

	@JsonIgnore
	private Integer installment;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantityOfInstallments() {
		return quantityOfInstallments;
	}

	public void setQuantityOfInstallments(Integer quantityOfInstallments) {
		this.quantityOfInstallments = quantityOfInstallments;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
