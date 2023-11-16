package com.ezen.biz.dao;

import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.ProductVO;

import utils.Criteria;

@Repository
public class ProductDAO {
	@Autowired
	private SqlSessionTemplate mybatis;

	// 새로운 상품
	public List<ProductVO> getNewProductList() {

		return mybatis.selectList("ProductMapper.getNewProductList");
	}

	// 베스트 상품
	public List<ProductVO> getBestProductList() {

		return mybatis.selectList("ProductMapper.getBestProductList");
	}

	// 상품 상세정보
	public ProductVO getProduct(ProductVO vo) {

		return mybatis.selectOne("ProductMapper.getProduct", vo);
	}

	// 종류별 상품정보
	public List<ProductVO> getProductListByKind(String kind) {

		return mybatis.selectList("ProductMapper.getProductListByKind", kind);

	}
	
	//상품목록의개수조회
	public int countProductList(String name) {
		
		return mybatis.selectOne("ProductMapper.countProductList", name);
	}
	
	//상품목록조회
	public List<ProductVO> listProduct(String name) {
		return mybatis.selectList("ProductMapper.listProduct", name);
	}
	
	/*
	 * 페이지별 상품 목록 조회
	 * 입력파라미터: 
	 * 						Criteria - 현재 페이지 정보
	 * 						name- 검색할 상품명
	 */
	public List<ProductVO> listProductWithPaging(Criteria criteria, String name) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("criteria", criteria);
		map.put("name", name);
		
		return mybatis.selectList("ProductMapper.listProductWithPaging", map);	
		
	}
	
	//상품추가
	public void insertProduct(ProductVO vo) {
		mybatis.insert("ProductMapper.insertProduct", vo);
	}
	
	//상품수정
	public void updateProduct(ProductVO vo) {
		mybatis.insert("ProductMapper.updateProduct", vo);
	}
	
	
	
	
	
	
	
	
	
}
