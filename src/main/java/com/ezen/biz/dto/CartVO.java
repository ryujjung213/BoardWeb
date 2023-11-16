package com.ezen.biz.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CartVO {
	private int cseq;
	 private String id;
	 private int pseq;
	 private String mname;
	 private String pname;
	 private int quantity;
	 private int price2;
	 private Date indate;

}
