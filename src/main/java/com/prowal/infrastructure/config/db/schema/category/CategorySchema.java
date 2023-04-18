package com.prowal.infrastructure.config.db.schema.category;

import java.io.Serializable;

import com.prowal.infrastructure.config.db.schema.user.UserSchema;

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

@Entity(name = "categories")
public class CategorySchema implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_sequence")
	@SequenceGenerator(name = "categories_sequence", sequenceName = "categories_sequence", allocationSize = 1)
	private Long id;

	@Column(name = "description")
	private String description;

	@Column(name = "type")
	@Enumerated(EnumType.ORDINAL)
	private TypeCategory type;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserSchema user;

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

	public TypeCategory getType() {
		return type;
	}

	public void setType(TypeCategory type) {
		this.type = type;
	}

	public UserSchema getUser() {
		return user;
	}

	public void setUser(UserSchema user) {
		this.user = user;
	}
}
