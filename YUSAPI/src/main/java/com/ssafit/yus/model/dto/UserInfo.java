package com.ssafit.yus.model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
	private String userId;
	private String userPassword;
	private float weight;
	private float height;
	private int age;
	private int gender;
	private String gymName;
	private String filepath;
	private int purpose;
	private int groupNo;
	private int userPoint;
}
