package com.ezen.biz.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ezen.biz.dto.OrderVO;

import utils.Criteria;

public interface OrderService {

	// 주문번호 생성
	int getMaxOseq();

	// 새로 주문 생성
	int insertOrder(OrderVO vo);

	// 주문 상세 정보 생성
	void insertOrderDetail(OrderVO vo);

	// 사용자별 주문내역 조회(장바구니에서 주문한 내역)
	List<OrderVO> getListOrderById(OrderVO vo);

	// 사용자별 미처리 주문 내역 조회
	List<Integer> getSeqOrdering(OrderVO vo);
	
	// 주문 전체 조회
	List<OrderVO> getListOrder(String mname);
	
	// 주문 상태 갱신	
	void updateOrderResult(int odseq);
	
	// 주문 목록의 전체 갯수 조회
	int countOrderList(String mname);

	// 페이지별 주문 목록 조회
	List<OrderVO> getListOrderWithPaging(Criteria criteria, String name);
	
}