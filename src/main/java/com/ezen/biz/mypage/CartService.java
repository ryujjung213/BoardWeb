package com.ezen.biz.mypage;

import java.util.List;

import com.ezen.biz.dto.CartVO;

public interface CartService {

	// ��ٱ��� ���
	void insertCart(CartVO vo);

	// ��ٱ��� ���
	List<CartVO> getlistCart(String id);

	// ��ٱ��� ���
	void deleteCart(int cseq);
	
	// ��ٱ��� ������Ʈ
	void updateCart(int cseq);

}