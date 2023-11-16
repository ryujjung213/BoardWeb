package com.ezen.biz.admin;

import com.ezen.biz.dto.AdminVO;

public interface AdminService {

	// 관리자 인증 매핑
	int adminCheck(AdminVO vo);
	
	// 관리자 정보 조회
	AdminVO getAdmin(String id);

}