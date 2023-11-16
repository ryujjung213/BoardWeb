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

	// 주문번호 생성
	public int selectMaxOseq() {

		return mybatis.selectOne("OrderMapper.selectMaxOseq");
	}

	// 새로 주문 생성
	public void insertOrder(OrderVO vo) {
		
		mybatis.insert("OrderMapper.insertOrder", vo);
	}

	// 주문 상세 정보 생성
	public void insertOrderDetail(OrderVO vo) {

		mybatis.insert("OrderMapper.insertOrderDetail", vo);
	}

	//사용자별 주문내역 조회(장바구니에서 주문한 내역)
	public List<OrderVO> listOrderById(OrderVO vo) {
		return mybatis.selectList("OrderMapper.listOrderById", vo);		
	}

	// 사용자별 미처리 주문 내역 조회
	public List<Integer> getSeqOrdering(OrderVO vo) {
		
		return mybatis.selectList("OrderMapper.selectSeqOrdering", vo);
	}
	
	// 주문 전체 조회
	public List<OrderVO> listOrder(String mname) {
		
		return mybatis.selectList("OrderMapper.listOrder", mname);
	}
	
	// 주문 상태 갱신	
	public void updateOrderResult(int odseq) {
		
		mybatis.update("OrderMapper.updateOrderResult", odseq);
	}
	
	// 주문 목록의 전체 갯수 조회
	public int countOrderList(String mname) {
		return mybatis.selectOne("OrderMapper.countOrderList", mname);
	}
	
	
	// 페이지별 주문 목록 조회
	public List<OrderVO> listOrderWithPaging(Criteria criteria, String name) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("criteria", criteria);
		map.put("name", name);
		
		return mybatis.selectList("OrderMapper.listOrderWithPaging", map);
	}
	
	
	
	
	
	
}
