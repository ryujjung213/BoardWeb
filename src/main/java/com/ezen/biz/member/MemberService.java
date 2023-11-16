package com.ezen.biz.member;

import java.util.List;

import com.ezen.biz.dto.AddressVO;
import com.ezen.biz.dto.MemberVO;

public interface MemberService {

	// ȸ�� ���� ��ȸ
	MemberVO getMember(String id);

	// ȸ�� ���� ����
	int confirmID(String id);

	// ȸ�� ���� ����
	void insertMember(MemberVO memberVO);
	
	// ���̸����� �ּ� ã��
	List<AddressVO> selectAddressByDong(String dong);
	
	// ȸ������
	
	int longinID(MemberVO vo) ;
	
	// �̸��� �̸��Ϸ� ���̵� ã��
	String getIdByNameAndEmail(MemberVO vo);
	
	// ���̵�, �̸�, �̸��Ϸ� ��й�ȣ ã��
	String getPwdByIdNameEmail(MemberVO vo);
	
	// ��й�ȣ ����	
	void changePassword(MemberVO vo);

}