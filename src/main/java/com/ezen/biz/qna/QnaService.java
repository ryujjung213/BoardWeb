package com.ezen.biz.qna;

import java.util.List;

import com.ezen.biz.dto.QnaVO;

public interface QnaService {

	// ȸ���� QnA ����� ��ȸ
	List<QnaVO> getListQna(String id);

	// QnA �� ��ȸ
	QnaVO getQna(int qseq);

	// QnA �Խñ� ���
	void insertQna(QnaVO vo);

}