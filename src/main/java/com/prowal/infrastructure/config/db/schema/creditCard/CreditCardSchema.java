package com.prowal.infrastructure.config.db.schema.creditCard;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import com.prowal.infrastructure.config.db.schema.account.AccountSchema;

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
@Table(name = "credit_cards")
public class CreditCardSchema implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credit_card_sequence")
	@SequenceGenerator(name = "credit_card_sequence", sequenceName = "credit_card_sequence", allocationSize = 1)
	private Long id;

	@Column(name = "description")
	private String description;

	@Column(name = "final_numbers")
	private String finalNumbers;

	@Column(name = "limit_value")
	private BigDecimal limitValue;

	@Column(name = "invoice_date")
	private String invoiceDate;

	@Column(name = "due_date")
	private String dueDate;

	@Column(name = "enabled")
	private Boolean enabled;

	@Column(name = "created_at", updatable = false)
	private Instant createdAt;

	@Column(name = "updated_at")
	private Instant updatedAt;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private AccountSchema account;

	public Long getId() {
		return id;
	}

	public BigDecimal getLimitValue() {
		return limitValue;
	}

	public void setLimitValue(BigDecimal limitValue) {
		this.limitValue = limitValue;
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

	public AccountSchema getAccount() {
		return account;
	}

	public void setAccount(AccountSchema account) {
		this.account = account;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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
