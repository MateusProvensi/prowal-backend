package com.prowal.vos.v1.input.ids;

import java.io.Serializable;

public class AccountIdInput implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	public AccountIdInput() {
	}

	public AccountIdInput(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
