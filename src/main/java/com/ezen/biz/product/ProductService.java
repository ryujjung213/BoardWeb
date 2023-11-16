package com.ezen.biz.product;

import java.util.List;

import com.ezen.biz.dto.ProductVO;

import utils.Criteria;

public interface ProductService {

	// 새로운 상품
	List<ProductVO> getNewProductList();

	// 베스트 상품
	List<ProductVO> getBestProductList();

	// 상품 상세정보
	ProductVO getProduct(ProductVO vo);

	// 종류별 상품정보
	List<ProductVO> getProductListByKind(String kind);

	// 상품목록의개수조회
	int countProductList(String name);

	// 상품목록조회
	List<ProductVO> getListProduct(String name);

	// 상품추가
	void insertProduct(ProductVO vo);

	// 상품수정
	void updateProduct(ProductVO vo);
	
	//페이지별 상품 목록 조회
	List<ProductVO> getListProductWithPaging(Criteria criteria, String name);

}