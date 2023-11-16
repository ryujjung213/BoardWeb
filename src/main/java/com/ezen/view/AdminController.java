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
		// (1)adminCheck ȣ��
		int result = adminService.adminCheck(vo);
		String message = "";
		// (2) ��ȸ ����� 1�̸� "admin_product_list" ��û
		if (result == 1) {
			model.addAttribute("admin", adminService.getAdmin(vo.getId()));

			return "redirect:admin_product_list";
		} else {
			// (3) ��ȸ ����� 0 �Ǵ� -1�̸� "��й�ȣ�� Ȯ���ϼ���"
			// "���̵� Ȯ���ϼ���."�� message�� ����
			if (result == 0) {
				model.addAttribute("message", "��й�ȣ�� Ȯ���ϼ���");
			} else if (result == -1) {
				model.addAttribute("message", "���̵� Ȯ���ϼ���");
			}
			return "admin/main";
		}

	}

	@GetMapping(value = "/admin_logout")
	public String adminLogout(SessionStatus status) {
		status.setComplete(); // ���� ����

		return "admin/main";
	}

	// ����¡ ó���� ���� ���� ��ǰ ���
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

		// (1)�������� ��ǰ ��� ��ȸ
		List<ProductVO> prodList = productService.getListProductWithPaging(criteria, name);

		// (2) ȭ�鿡 ǥ���� ������ ��ư�� ���� ����
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		// �� �Խñ��� �� ����
		pageMaker.setTotalCount(productService.countProductList(name));

		model.addAttribute("productListSize", prodList.size());
		model.addAttribute("productList", prodList);
		model.addAttribute("pageMaker", pageMaker); // ������ ��ȣ ǥ�ÿ� ������

		return "admin/product/productList";
	}

	@PostMapping(value = "/admin_product_write")
	public String adminProductWriteAction(ProductVO vo, HttpSession session,
			@RequestParam(value = "product_image") MultipartFile uploadFile) {
		// �̹��� ������ ���ε�Ǿ����� Ȯ��
		if (!uploadFile.isEmpty()) { // ������ ���ε��.
			String fileName = uploadFile.getOriginalFilename();
			vo.setImage(fileName);

			// ���ε� �̹��� ����
			// session.getServletContext() - ������Ʈ ���� ���� ����
			String imagePath = session.getServletContext().getRealPath("WEB-INF/resources/prdocut_images/");
			System.out.println("ImagePath = " + imagePath);

			try {
				uploadFile.transferTo(new File(imagePath + fileName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		// �Է��� ��ǰ���� ����
		productService.insertProduct(vo);

		return "redirect:admin_product_list";
	}

	@PostMapping(value = "/admin_product_write_form")
	public String adminProductWriteView(Model model) {
		String[] kindList = { "��", "����", "����", "������", "����Ŀ��", "����" };

		model.addAttribute("kindList", kindList);

		return "admin/product/productWrite";
	}

	@RequestMapping(value = "/admin_product_detail")
	public String adminProductDetail(Criteria criteria, ProductVO vo, Model model) {
		String[] kindList = { "", "��", "����", "����", "������", "����Ŀ��", "����" };
		ProductVO product = productService.getProduct(vo);

		model.addAttribute("productVO", product);
		int index = Integer.parseInt(product.getKind());
		model.addAttribute("kind", kindList[index]);
		model.addAttribute("criteria", criteria);

		return "admin/product/productDetail";

	}

	@PostMapping(value = "/admin_product_update_form")
	public String adminProductUpdateView(ProductVO vo, Model model) {
		String[] kindList = { "��", "����", "����", "������", "����Ŀ��", "����" };

		ProductVO product = productService.getProduct(vo);

		model.addAttribute("productVO", product);
		model.addAttribute("kindList", kindList);

		return "admin/product/productUpdate";
	}

	@PostMapping("/admin_product_update")
	public String adminProductUpdate(ProductVO vo, HttpSession session,
			@RequestParam(value = "product_image") MultipartFile uploadFile,
			@RequestParam(value = "nonmakeImg") String org_image) {

		if (!uploadFile.isEmpty()) { // ���ο� �̹��� ������ ���ε��.
			String fileName = uploadFile.getOriginalFilename();
			vo.setImage(fileName);

			// ���ε� �̹��� ����
			// session.getServletContext() - ������Ʈ ���� ���� ����
			String imagePath = session.getServletContext().getRealPath("WEB-INF/resources/prdocut_images/");
			System.out.println("ImagePath = " + imagePath);

			try {
				uploadFile.transferTo(new File(imagePath + fileName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		} else { // ���� �̹��� ���
			vo.setImage(org_image);
		}

		// ����Ʈ��ǰ, �űԻ�ǰ ����
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
	 * ��ü �ֹ� ����
	 */
	/*
	@GetMapping(value = "/admin_order_list")
	public String adminOrderList(@RequestParam(value = "key", defaultValue = "") String mname, Model model) {

		List<OrderVO> orderList = orderService.getListOrder(mname);

		model.addAttribute("orderList", orderList);

		return "admin/order/orderList";
	}
	*/
	
	// �ֹ�����Ʈ ����¡ ó��
	@RequestMapping(value="/admin_order_list")
	public String adminOrderList(@RequestParam(value = "key", defaultValue = "") String name, 
												Criteria criteria, Model model) {
		// (1)�������� ��ǰ ��� ��ȸ
		List<OrderVO> orderList = orderService.getListOrderWithPaging(criteria, name);
		
		// (2) ȭ�鿡 ǥ���� ������ ��ư�� ���� ����
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		// �� �Խñ��� �� ����
		pageMaker.setTotalCount(productService.countProductList(name));

		model.addAttribute("orderListSize", orderList.size());
		model.addAttribute("orderList", orderList);
		model.addAttribute("pageMaker", pageMaker); // ������ ��ȣ ǥ�ÿ� ������
		
		return "admin/order/orderList";
	}

	/*
	 * �ֹ� �Ϸ� ó��(�Ա� Ȯ��)
	 */
	@GetMapping("/admin_order_save")
	public String adminOrderSave(@RequestParam(value = "result") int[] odseq) {
		for (int i = 0; i < odseq.length; i++) {
			orderService.updateOrderResult(odseq[i]);
		}
		return "redirect:admin_order_list";
	}
	
	// �ֹ�����Ʈ ����¡ ó��
	
	

	
	

}
