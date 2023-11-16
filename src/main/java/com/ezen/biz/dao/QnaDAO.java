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

	// ȸ���� QnA ����� ��ȸ
	public List<QnaVO> listQna(String id) {

		return mybatis.selectList("QnaMapper.listQna", id);
	}

	// QnA �� ��ȸ
	public QnaVO getQna(int qseq) {

		return mybatis.selectOne("QnaMapper.getQna", qseq);
	}

	// QnA �Խñ� ���
	public void insertQna(QnaVO vo) {

		mybatis.insert("QnaMapper.insertQna", vo);
	}

}
