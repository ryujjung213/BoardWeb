package com.ezen.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.QnaVO;

@Repository
public class QnaDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	// 회원별 QnA 목록을 조회
	public List<QnaVO> listQna(String id) {

		return mybatis.selectList("QnaMapper.listQna", id);
	}

	// QnA 상세 조회
	public QnaVO getQna(int qseq) {

		return mybatis.selectOne("QnaMapper.getQna", qseq);
	}

	// QnA 게시글 등록
	public void insertQna(QnaVO vo) {

		mybatis.insert("QnaMapper.insertQna", vo);
	}

}
