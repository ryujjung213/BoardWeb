package com.ezen.biz.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {
	
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String zip_num;
	private String address;
	private String phone;
	private String useyn;
	private Date redate;

}
