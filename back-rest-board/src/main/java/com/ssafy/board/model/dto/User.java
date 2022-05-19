package com.ssafy.board.model.dto;

import lombok.Data;

@Data
public class User {
	private String id;
	private String password;
	private String authority;
}
