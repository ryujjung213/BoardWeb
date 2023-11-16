package com.ezen.view;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ezen.biz.dto.MemberVO;
import com.ezen.biz.dto.QnaVO;
import com.ezen.biz.qna.QnaService;

@Controller
public class QnaController {

	@Autowired
	private QnaService qnaService;

	// QnA ��ȸ
	@GetMapping(value = "qna_list")
	public String qnaListView(HttpSession session, Model model) {
		// (1) �α��� Ȯ��
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) { // �α����� �ȵ� ���
			return "member/login"; // �α��� ȭ�� ǥ��
		} else {
			// (2) Qna ��� ��ȸ
			List<QnaVO> qnaList = qnaService.getListQna(loginUser.getId());

			// (3)������ ���� �� ȭ�� ����
			model.addAttribute("qnaList", qnaList);
			return "qna/qnaList";
		}

	}

	// ��QnA ����
	@GetMapping(value = "/qna_write_form")
	public String qnaWriteView(HttpSession session) {
		// �α��� Ȯ��
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) { // �α����� �ȵ� ���
			return "member/login"; // �α��� ȭ�� ǥ��
		} else {
			return "qna/qnaWrite";
		}

	}

	@PostMapping(value = "qna_write")
	public String qnaWriteAction(QnaVO vo, HttpSession session) {
		// �α��� Ȯ��
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser == null) { // �α����� �ȵ� ���
			return "member/login"; // �α��� ȭ�� ǥ��
		} else {
			vo.setId(loginUser.getId());

			qnaService.insertQna(vo);

			return "redirect:qna_list";
		}

	}

	// QnA �Խñ� �� ����
	@GetMapping(value = "/qna_view")
	public String qnaView(QnaVO vo, HttpSession session, Model model) {
		// �α��� Ȯ��
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser == null) { // �α����� �ȵ� ���
			return "member/login"; // �α��� ȭ�� ǥ��
		} else {
			QnaVO qna = qnaService.getQna(vo.getQseq());

			model.addAttribute("qnaVO", qna);

			return "qna/qnaView";

		}

	}

}
