package com.ezen.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.AddressVO;
import com.ezen.biz.dto.MemberVO;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	// ȸ�� ����(�α��� ó��)
	// id�� �������� ������  -1 ����
	// ��й�ȣ�� Ʋ����� 0 ����
	// ���� ������� ��� 1 ����
	public int longinID(MemberVO vo) {
		int result = -1; // ���� ��� ����
		
		// ���̺� ����� ��й�ȣ ��ȸ
		String pwd = mybatis.selectOne("MemberMapper.confirmID", vo.getId()) ;
		
		if(pwd == null) {
			result = -1;
		} else if(pwd.equals(vo.getPwd())) {
			result = 1;
		} else {
			result = 0;
		}
		return result;
	}
	

	// ȸ�� ���� ��ȸ
	public MemberVO getMember(String id) {

		return mybatis.selectOne("MemberMapper.getMember", id);
	}

	// ȸ�� ���� ����
	public int confirmID(String id) {

		String pwd = mybatis.selectOne("MemberMapper.confirmID", id);

		if (pwd != null) { // ȸ���� ������ ���
			return 1;
		} else {
			return -1;
		}
	}

	// ȸ�� ���� ����
	public void insertMember(MemberVO memberVO) {

		mybatis.insert("MemberMapper.insertMember", memberVO);
	}
	
	// ���̸����� �ּ� ã��
	
	public List<AddressVO> selectAddressByDong(String dong) {
		
		return mybatis.selectList("MemberMapper.selectAddressByDong", dong);
	}
	
	// �̸��� �̸��Ϸ� ���̵� ã��
	public String selectIdByNameAndEmail(MemberVO vo) {
		return mybatis.selectOne("MemberMapper.selectIdByNameAndEmail", vo);
	}
	
	// ���̵�, �̸�, �̸��Ϸ� ��й�ȣ ã��
	public String selectPwdByIdNameEmail(MemberVO vo) {
		return mybatis.selectOne("MemberMapper.selectPwdByIdNameEmail", vo);
	}
	
	// ��й�ȣ ����
	
	public void changePassword(MemberVO vo) {
		
		mybatis.update("MemberMapper.changePwd", vo);
	}
	
}