package com.ezen.biz.member;

import java.util.List;

import com.ezen.biz.dto.AddressVO;
import com.ezen.biz.dto.MemberVO;

public interface MemberService {

	// 회원 정보 조회
	MemberVO getMember(String id);

	// 회원 존재 여부
	int confirmID(String id);

	// 회원 정보 저장
	void insertMember(MemberVO memberVO);
	
	// 동이름으로 주소 찾기
	List<AddressVO> selectAddressByDong(String dong);
	
	// 회원인증
	
	int longinID(MemberVO vo) ;
	
	// 이름과 이메일로 아이디 찾기
	String getIdByNameAndEmail(MemberVO vo);
	
	// 아이디, 이름, 이메일로 비밀번호 찾기
	String getPwdByIdNameEmail(MemberVO vo);
	
	// 비밀번호 수정	
	void changePassword(MemberVO vo);

}