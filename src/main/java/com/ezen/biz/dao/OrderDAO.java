package com.ezen.biz.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.OrderVO;

import utils.Criteria;

@Repository
public class OrderDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	// �ֹ���ȣ ����
	public int selectMaxOseq() {

		return mybatis.selectOne("OrderMapper.selectMaxOseq");
	}

	// ���� �ֹ� ����
	public void insertOrder(OrderVO vo) {
		
		mybatis.insert("OrderMapper.insertOrder", vo);
	}

	// �ֹ� �� ���� ����
	public void insertOrderDetail(OrderVO vo) {

		mybatis.insert("OrderMapper.insertOrderDetail", vo);
	}

	//����ں� �ֹ����� ��ȸ(��ٱ��Ͽ��� �ֹ��� ����)
	public List<OrderVO> listOrderById(OrderVO vo) {
		return mybatis.selectList("OrderMapper.listOrderById", vo);		
	}

	// ����ں� ��ó�� �ֹ� ���� ��ȸ
	public List<Integer> getSeqOrdering(OrderVO vo) {
		
		return mybatis.selectList("OrderMapper.selectSeqOrdering", vo);
	}
	
	// �ֹ� ��ü ��ȸ
	public List<OrderVO> listOrder(String mname) {
		
		return mybatis.selectList("OrderMapper.listOrder", mname);
	}
	
	// �ֹ� ���� ����	
	public void updateOrderResult(int odseq) {
		
		mybatis.update("OrderMapper.updateOrderResult", odseq);
	}
	
	// �ֹ� ����� ��ü ���� ��ȸ
	public int countOrderList(String mname) {
		return mybatis.selectOne("OrderMapper.countOrderList", mname);
	}
	
	
	// �������� �ֹ� ��� ��ȸ
	public List<OrderVO> listOrderWithPaging(Criteria criteria, String name) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("criteria", criteria);
		map.put("name", name);
		
		return mybatis.selectList("OrderMapper.listOrderWithPaging", map);
	}
	
	
	
	
	
	
}
