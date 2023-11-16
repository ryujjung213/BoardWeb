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

	// QnA 조회
	@GetMapping(value = "qna_list")
	public String qnaListView(HttpSession session, Model model) {
		// (1) 로그인 확인
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) { // 로그인이 안된 경우
			return "member/login"; // 로그인 화면 표시
		} else {
			// (2) Qna 목록 조회
			List<QnaVO> qnaList = qnaService.getListQna(loginUser.getId());

			// (3)데이터 전송 및 화면 송출
			model.addAttribute("qnaList", qnaList);
			return "qna/qnaList";
		}

	}

	// 새QnA 쓰기
	@GetMapping(value = "/qna_write_form")
	public String qnaWriteView(HttpSession session) {
		// 로그인 확인
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) { // 로그인이 안된 경우
			return "member/login"; // 로그인 화면 표시
		} else {
			return "qna/qnaWrite";
		}

	}

	@PostMapping(value = "qna_write")
	public String qnaWriteAction(QnaVO vo, HttpSession session) {
		// 로그인 확인
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser == null) { // 로그인이 안된 경우
			return "member/login"; // 로그인 화면 표시
		} else {
			vo.setId(loginUser.getId());

			qnaService.insertQna(vo);

			return "redirect:qna_list";
		}

	}

	// QnA 게시글 상세 보기
	@GetMapping(value = "/qna_view")
	public String qnaView(QnaVO vo, HttpSession session, Model model) {
		// 로그인 확인
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser == null) { // 로그인이 안된 경우
			return "member/login"; // 로그인 화면 표시
		} else {
			QnaVO qna = qnaService.getQna(vo.getQseq());

			model.addAttribute("qnaVO", qna);

			return "qna/qnaView";

		}

	}

}
