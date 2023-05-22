package com.prowal.infrastructure.config.db.schema.creditCardTransaction;

import java.io.Serializable;
import java.time.Instant;

import com.prowal.infrastructure.config.db.schema.creditCard.CreditCardSchema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "credit_cards_transactions")
public class CreditCardTransactionSchema implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credit_cards_transactions_sequence")
	@SequenceGenerator(name = "credit_cards_transactions_sequence", sequenceName = "credit_cards_transactions_sequence", allocationSize = 1)
	private Long id;

	@Column(name = "description")
	private String description;

	@Column(name = "date")
	private Instant date;

	@Column(name = "installment")
	private Integer installment;

	@Column(name = "created_at", updatable = false)
	private Instant createdAt;

	@Column(name = "updated_at")
	private Instant updatedAt;

	@ManyToOne
	@JoinColumn(name = "credit_card_id")
	private CreditCardSchema creditCard;

	public CreditCardTransactionSchema() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public CreditCardSchema getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCardSchema creditCard) {
		this.creditCard = creditCard;
	}
}
