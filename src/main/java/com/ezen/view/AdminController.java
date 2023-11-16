package com.ezen.view;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.biz.admin.AdminService;
import com.ezen.biz.dto.AdminVO;
import com.ezen.biz.dto.OrderVO;
import com.ezen.biz.dto.ProductVO;
import com.ezen.biz.order.OrderService;
import com.ezen.biz.product.ProductService;

import utils.Criteria;
import utils.PageMaker;

@Controller
@SessionAttributes("admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;

	@GetMapping(value = "/admin_login_form")
	public String adminLoginView() {

		return "admin/main";

	}

	@PostMapping(value = "/admin_login")
	public String adminLoginAction(AdminVO vo, Model model) {
		// (1)adminCheck 호출
		int result = adminService.adminCheck(vo);
		String message = "";
		// (2) 조회 결과가 1이면 "admin_product_list" 요청
		if (result == 1) {
			model.addAttribute("admin", adminService.getAdmin(vo.getId()));

			return "redirect:admin_product_list";
		} else {
			// (3) 조회 결과가 0 또는 -1이면 "비밀번호를 확인하세요"
			// "아이디를 확인하세요."를 message에 저장
			if (result == 0) {
				model.addAttribute("message", "비밀번호를 확인하세요");
			} else if (result == -1) {
				model.addAttribute("message", "아이디를 확인하세요");
			}
			return "admin/main";
		}

	}

	@GetMapping(value = "/admin_logout")
	public String adminLogout(SessionStatus status) {
		status.setComplete(); // 세션 해지

		return "admin/main";
	}

	// 페이징 처리를 하지 않은 상품 목록
	/*
	 * @RequestMapping(value = "admin_product_list") public String
	 * adminProductList(@RequestParam(value = "key", defaultValue = "") String name,
	 * Model model) {
	 * 
	 * List<ProductVO> prodList = productService.getListProduct(name);
	 * 
	 * model.addAttribute("productListSize", prodList.size());
	 * model.addAttribute("productList", prodList);
	 * 
	 * return "admin/product/productList"; }
	 */

	@RequestMapping(value = "admin_product_list")
	public String adminProductList(@RequestParam(value = "key", defaultValue = "") String name, 
			Criteria criteria, Model model) {

		// (1)페이지별 상품 목록 조회
		List<ProductVO> prodList = productService.getListProductWithPaging(criteria, name);

		// (2) 화면에 표시할 페이지 버튼의 정보 설정
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		// 총 게시글의 수 저장
		pageMaker.setTotalCount(productService.countProductList(name));

		model.addAttribute("productListSize", prodList.size());
		model.addAttribute("productList", prodList);
		model.addAttribute("pageMaker", pageMaker); // 페이지 번호 표시용 데이터

		return "admin/product/productList";
	}

	@PostMapping(value = "/admin_product_write")
	public String adminProductWriteAction(ProductVO vo, HttpSession session,
			@RequestParam(value = "product_image") MultipartFile uploadFile) {
		// 이미지 파일이 업로드되었는지 확인
		if (!uploadFile.isEmpty()) { // 파일이 업로드됨.
			String fileName = uploadFile.getOriginalFilename();
			vo.setImage(fileName);

			// 업로드 이미지 저장
			// session.getServletContext() - 프로젝트 관련 정보 리턴
			String imagePath = session.getServletContext().getRealPath("WEB-INF/resources/prdocut_images/");
			System.out.println("ImagePath = " + imagePath);

			try {
				uploadFile.transferTo(new File(imagePath + fileName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		// 입력한 상품정보 저장
		productService.insertProduct(vo);

		return "redirect:admin_product_list";
	}

	@PostMapping(value = "/admin_product_write_form")
	public String adminProductWriteView(Model model) {
		String[] kindList = { "힐", "부츠", "샌달", "슬리퍼", "스니커즈", "세일" };

		model.addAttribute("kindList", kindList);

		return "admin/product/productWrite";
	}

	@RequestMapping(value = "/admin_product_detail")
	public String adminProductDetail(Criteria criteria, ProductVO vo, Model model) {
		String[] kindList = { "", "힐", "부츠", "샌달", "슬리퍼", "스니커즈", "세일" };
		ProductVO product = productService.getProduct(vo);

		model.addAttribute("productVO", product);
		int index = Integer.parseInt(product.getKind());
		model.addAttribute("kind", kindList[index]);
		model.addAttribute("criteria", criteria);

		return "admin/product/productDetail";

	}

	@PostMapping(value = "/admin_product_update_form")
	public String adminProductUpdateView(ProductVO vo, Model model) {
		String[] kindList = { "힐", "부츠", "샌달", "슬리퍼", "스니커즈", "세일" };

		ProductVO product = productService.getProduct(vo);

		model.addAttribute("productVO", product);
		model.addAttribute("kindList", kindList);

		return "admin/product/productUpdate";
	}

	@PostMapping("/admin_product_update")
	public String adminProductUpdate(ProductVO vo, HttpSession session,
			@RequestParam(value = "product_image") MultipartFile uploadFile,
			@RequestParam(value = "nonmakeImg") String org_image) {

		if (!uploadFile.isEmpty()) { // 새로운 이미지 파일이 업로드됨.
			String fileName = uploadFile.getOriginalFilename();
			vo.setImage(fileName);

			// 업로드 이미지 저장
			// session.getServletContext() - 프로젝트 관련 정보 리턴
			String imagePath = session.getServletContext().getRealPath("WEB-INF/resources/prdocut_images/");
			System.out.println("ImagePath = " + imagePath);

			try {
				uploadFile.transferTo(new File(imagePath + fileName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		} else { // 기존 이미지 사용
			vo.setImage(org_image);
		}

		// 베스트상품, 신규상품 설정
		System.out.println("useryn = " + vo.getUseyn());
		System.out.println("bestyn = " + vo.getBestyn());
		if (vo.getUseyn() == null) {
			vo.setUseyn("n");
		} else {
			vo.setUseyn("y");
		}

		if (vo.getBestyn() == null) {
			vo.setBestyn("n");
		} else {
			vo.setBestyn("y");
		}

		productService.updateProduct(vo);

		return "redirect:admin_product_list";

	}

	/*
	 * 전체 주문 내역
	 */
	/*
	@GetMapping(value = "/admin_order_list")
	public String adminOrderList(@RequestParam(value = "key", defaultValue = "") String mname, Model model) {

		List<OrderVO> orderList = orderService.getListOrder(mname);

		model.addAttribute("orderList", orderList);

		return "admin/order/orderList";
	}
	*/
	
	// 주문리스트 페이징 처리
	@RequestMapping(value="/admin_order_list")
	public String adminOrderList(@RequestParam(value = "key", defaultValue = "") String name, 
												Criteria criteria, Model model) {
		// (1)페이지별 상품 목록 조회
		List<OrderVO> orderList = orderService.getListOrderWithPaging(criteria, name);
		
		// (2) 화면에 표시할 페이지 버튼의 정보 설정
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		// 총 게시글의 수 저장
		pageMaker.setTotalCount(productService.countProductList(name));

		model.addAttribute("orderListSize", orderList.size());
		model.addAttribute("orderList", orderList);
		model.addAttribute("pageMaker", pageMaker); // 페이지 번호 표시용 데이터
		
		return "admin/order/orderList";
	}

	/*
	 * 주문 완료 처리(입금 확인)
	 */
	@GetMapping("/admin_order_save")
	public String adminOrderSave(@RequestParam(value = "result") int[] odseq) {
		for (int i = 0; i < odseq.length; i++) {
			orderService.updateOrderResult(odseq[i]);
		}
		return "redirect:admin_order_list";
	}
	
	// 주문리스트 페이징 처리
	
	

	
	

}
