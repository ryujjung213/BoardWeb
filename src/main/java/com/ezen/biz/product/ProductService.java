package com.ezen.biz.product;

import java.util.List;

import com.ezen.biz.dto.ProductVO;

import utils.Criteria;

public interface ProductService {

	// ���ο� ��ǰ
	List<ProductVO> getNewProductList();

	// ����Ʈ ��ǰ
	List<ProductVO> getBestProductList();

	// ��ǰ ������
	ProductVO getProduct(ProductVO vo);

	// ������ ��ǰ����
	List<ProductVO> getProductListByKind(String kind);

	// ��ǰ����ǰ�����ȸ
	int countProductList(String name);

	// ��ǰ�����ȸ
	List<ProductVO> getListProduct(String name);

	// ��ǰ�߰�
	void insertProduct(ProductVO vo);

	// ��ǰ����
	void updateProduct(ProductVO vo);
	
	//�������� ��ǰ ��� ��ȸ
	List<ProductVO> getListProductWithPaging(Criteria criteria, String name);

}