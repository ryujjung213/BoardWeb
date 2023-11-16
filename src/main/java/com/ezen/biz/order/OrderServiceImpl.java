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
		// (1) 신규 주문 번호 생성
		int oseq = orderDao.selectMaxOseq();

		// (2) 신규 주문 주문테이블에 저장
		vo.setOseq(oseq);
		orderDao.insertOrder(vo);

		// (3) 장바구니 목록을 읽어 주문 상세 테이블에 저장
		List<CartVO> cartList = cartService.getlistCart(vo.getId());

		for (CartVO cart : cartList) {
			OrderVO order = new OrderVO();

			order.setOseq(oseq);
			order.setPseq(cart.getPseq());
			order.setQuantity(cart.getQuantity());

			insertOrderDetail(order);

			// 장바구니 테이블 업데이트(result를 '2'(처리))
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
