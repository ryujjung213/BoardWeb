package com.ezen.biz.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.OrderDAO;
import com.ezen.biz.dto.CartVO;
import com.ezen.biz.dto.OrderVO;
import com.ezen.biz.mypage.CartService;

import utils.Criteria;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDao;
	@Autowired
	private CartService cartService;

	@Override
	public int getMaxOseq() {

		return orderDao.selectMaxOseq();
	}

	@Override
	public int insertOrder(OrderVO vo) {
		// (1) �ű� �ֹ� ��ȣ ����
		int oseq = orderDao.selectMaxOseq();

		// (2) �ű� �ֹ� �ֹ����̺� ����
		vo.setOseq(oseq);
		orderDao.insertOrder(vo);

		// (3) ��ٱ��� ����� �о� �ֹ� �� ���̺� ����
		List<CartVO> cartList = cartService.getlistCart(vo.getId());

		for (CartVO cart : cartList) {
			OrderVO order = new OrderVO();

			order.setOseq(oseq);
			order.setPseq(cart.getPseq());
			order.setQuantity(cart.getQuantity());

			insertOrderDetail(order);

			// ��ٱ��� ���̺� ������Ʈ(result�� '2'(ó��))
			cartService.updateCart(cart.getCseq());
		}
		return oseq;

	}

	@Override
	public void insertOrderDetail(OrderVO vo) {

		orderDao.insertOrderDetail(vo);

	}

	@Override
	public List<OrderVO> getListOrderById(OrderVO vo) {

		return orderDao.listOrderById(vo);
	}

	@Override
	public List<Integer> getSeqOrdering(OrderVO vo) {

		return orderDao.getSeqOrdering(vo);
	}

	@Override
	public List<OrderVO> getListOrder(String mname) {

		return orderDao.listOrder(mname);
	}

	@Override
	public void updateOrderResult(int odseq) {

		orderDao.updateOrderResult(odseq);
	}

	@Override
	public int countOrderList(String mname) {
		// TODO Auto-generated method stub
		return orderDao.countOrderList(mname);
	}

	@Override
	public List<OrderVO> getListOrderWithPaging(Criteria criteria, String name) {
		
		return orderDao.listOrderWithPaging(criteria, name);
	}

}
