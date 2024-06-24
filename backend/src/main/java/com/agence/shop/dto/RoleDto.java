package com.agence.shop.dto;

import java.io.Serializable;

import com.agence.shop.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String authority;
	
	public RoleDto(Role role) {
		id = role.getId();
		authority = role.getAuthority();
	}

}
