package com.ezen.biz.admin;

import com.ezen.biz.dto.AdminVO;

public interface AdminService {

	// ������ ���� ����
	int adminCheck(AdminVO vo);
	
	// ������ ���� ��ȸ
	AdminVO getAdmin(String id);

}