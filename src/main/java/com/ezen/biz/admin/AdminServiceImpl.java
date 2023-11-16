package com.ezen.biz.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.AdminDAO;
import com.ezen.biz.dto.AdminVO;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDAO adminDao;

	// 관리자 인증
	@Override
	public int adminCheck(AdminVO vo) {
		String pwd =adminDao.adminCheck(vo.getId());
		
		if(pwd.equals(vo.getPwd())) {
			return 1;	// 정상적인 사용자
		} else if(pwd == null) {
			return -1;	// 관리자가 존재하지 않음
		} else {
			return 0;
		}
	}

	@Override
	public AdminVO getAdmin(String id) {
		
		return adminDao.getAdmin(id);
	}

}
