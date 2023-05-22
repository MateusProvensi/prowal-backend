package com.prowal.infrastructure.config.db.schema.account;

import java.io.Serializable;
import java.time.Instant;

import com.prowal.infrastructure.config.db.schema.user.UserSchema;

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
@Table(name = "accounts")
public class AccountSchema implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accounts_sequence")
	@SequenceGenerator(name = "accounts_sequence", sequenceName = "accounts_sequence", allocationSize = 1)
	private Long id;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "user_id", updatable = false)
	private UserSchema user;

	@Column(name = "enabled")
	private Boolean enabled;

	@Column(name = "created_at", updatable = false)
	private Instant createdAt;

	@Column(name = "updated_at")
	private Instant updatedAt;

	public AccountSchema() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserSchema getUser() {
		return user;
	}

	public void setUser(UserSchema user) {
		this.user = user;
	}
}
