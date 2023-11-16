package com.ezen.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.CartVO;

@Repository
public class CartDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	// ��ٱ��� ���
	public void insertCart(CartVO vo) {
		mybatis.insert("CartMapper.insertCart", vo);

	}

	// ��ٱ��� ���
	public List<CartVO> listCart(String id) {

		return mybatis.selectList("CartMapper.listCart", id);
	}

	// ��ٱ��� ���
	public void deleteCart(int cseq) {
		mybatis.delete("CartMapper.deleteCart", cseq);

	}

	// ��ٱ��� ������Ʈ
	public void updateCart(int cseq) {
		mybatis.update("CartMapper.updateCart", cseq);
	}
}
