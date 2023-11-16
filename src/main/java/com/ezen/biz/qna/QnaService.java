package com.ezen.biz.qna;

import java.util.List;

import com.ezen.biz.dto.QnaVO;

public interface QnaService {

	// 회원별 QnA 목록을 조회
	List<QnaVO> getListQna(String id);

	// QnA 상세 조회
	QnaVO getQna(int qseq);

	// QnA 게시글 등록
	void insertQna(QnaVO vo);

}