package com.ezen.biz.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ezen.biz.dto.OrderVO;

import utils.Criteria;

public interface OrderService {

	// �ֹ���ȣ ����
	int getMaxOseq();

	// ���� �ֹ� ����
	int insertOrder(OrderVO vo);

	// �ֹ� �� ���� ����
	void insertOrderDetail(OrderVO vo);

	// ����ں� �ֹ����� ��ȸ(��ٱ��Ͽ��� �ֹ��� ����)
	List<OrderVO> getListOrderById(OrderVO vo);

	// ����ں� ��ó�� �ֹ� ���� ��ȸ
	List<Integer> getSeqOrdering(OrderVO vo);
	
	// �ֹ� ��ü ��ȸ
	List<OrderVO> getListOrder(String mname);
	
	// �ֹ� ���� ����	
	void updateOrderResult(int odseq);
	
	// �ֹ� ����� ��ü ���� ��ȸ
	int countOrderList(String mname);

	// �������� �ֹ� ��� ��ȸ
	List<OrderVO> getListOrderWithPaging(Criteria criteria, String name);
	
}