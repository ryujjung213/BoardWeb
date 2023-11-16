package com.ezen.biz.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.MemberDAO;
import com.ezen.biz.dto.AddressVO;
import com.ezen.biz.dto.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDao;

	@Override
	public MemberVO getMember(String id) {

		return memberDao.getMember(id);
	}

	@Override
	public int confirmID(String id) {

		return memberDao.confirmID(id);
	}

	@Override
	public void insertMember(MemberVO memberVO) {

		memberDao.insertMember(memberVO);
	}

	@Override
	public List<AddressVO> selectAddressByDong(String dong) {
		
		return memberDao.selectAddressByDong(dong);
	}

	@Override
	public int longinID(MemberVO vo) {
		
		return memberDao.longinID(vo);
	}

	@Override
	public String getIdByNameAndEmail(MemberVO vo) {
		
		return memberDao.selectIdByNameAndEmail(vo);
	}

	@Override
	public String getPwdByIdNameEmail(MemberVO vo) {

		return memberDao.selectPwdByIdNameEmail(vo);
	}

	@Override
	public void changePassword(MemberVO vo) {
		
		memberDao.changePassword(vo);
		
	}


}
