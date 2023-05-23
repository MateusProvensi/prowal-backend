package com.prowal.infrastructure.config.db.schema.transaction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.prowal.infrastructure.config.db.schema.account.AccountSchema;
import com.prowal.infrastructure.config.db.schema.category.CategorySchema;
import com.prowal.infrastructure.config.db.schema.creditCardTransaction.CreditCardTransactionSchema;
import com.prowal.infrastructure.config.db.schema.subcategory.SubCategorySchema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class TransactionSchema implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactions_sequence")
	@SequenceGenerator(name = "transactions_sequence", sequenceName = "transactions_sequence", allocationSize = 1)
	private Long id;

	@Column(name = "description")
	private String description;

	@Column(name = "type", updatable = false)
	@Enumerated(EnumType.ORDINAL)
	private TypeTransaction type;

	@Column(name = "date")
	private Instant date;

	@Column(name = "value")
	private BigDecimal value;

	@Column(name = "created_at", updatable = false)
	private Instant createdAt;

	@Column(name = "updated_at")
	private Instant updatedAt;

	@ManyToOne
	@JoinColumn(name = "account_primary_id")
	private AccountSchema primaryAccount;

	@ManyToOne
	@JoinColumn(name = "account_secondary_id")
	private AccountSchema secondaryAccount;

	@ManyToOne
	@JoinColumn(name = "category_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private CategorySchema category;

	@ManyToOne
	@JoinColumn(name = "credit_card_transaction_id", updatable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	private CreditCardTransactionSchema creditCardTransaction;

	@ManyToOne
	@JoinColumn(name = "subcategory_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private SubCategorySchema subCategory;

	public TransactionSchema() {
	}

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

	public TypeTransaction getType() {
		return type;
	}

	public void setType(TypeTransaction type) {
		this.type = type;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
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

	public AccountSchema getPrimaryAccount() {
		return primaryAccount;
	}

	public void setPrimaryAccount(AccountSchema primaryAccount) {
		this.primaryAccount = primaryAccount;
	}

	public AccountSchema getSecondaryAccount() {
		return secondaryAccount;
	}

	public void setSecondaryAccount(AccountSchema secondaryAccount) {
		this.secondaryAccount = secondaryAccount;
	}

	public CategorySchema getCategory() {
		return category;
	}

	public void setCategory(CategorySchema category) {
		this.category = category;
	}

	public CreditCardTransactionSchema getCreditCardTransaction() {
		return creditCardTransaction;
	}

	public void setCreditCardTransaction(CreditCardTransactionSchema creditCardTransaction) {
		this.creditCardTransaction = creditCardTransaction;
	}

	public SubCategorySchema getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategorySchema subCategory) {
		this.subCategory = subCategory;
	}
}
