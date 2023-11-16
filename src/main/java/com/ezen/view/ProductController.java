package com.ezen.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.biz.dto.ProductVO;
import com.ezen.biz.product.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/product_detail", method = RequestMethod.GET)
	public String productDetailAction(ProductVO vo, Model model) {
		// (1) ��ǰ �� ��ȸ
		ProductVO product = productService.getProduct(vo);

		// (2) request ���� ��ü�� ��� ����
		model.addAttribute("productVO", product);

		// (3) ȭ�� ȣ��
		return "product/productDetail";
	}

	@GetMapping(value = "/category")
	public String prdouctKindAction(ProductVO vo, Model model) {
		// (1) ī�װ��� ��ǰ ��ȸ
		List<ProductVO> productList = productService.getProductListByKind(vo.getKind());

		// (2) request ���� ��ü�� ��� ����
		model.addAttribute("productKindList", productList);

		// (3) ȭ�� ȣ��
		return "product/productKind";

	}
}
