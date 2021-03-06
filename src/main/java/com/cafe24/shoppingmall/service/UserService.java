package com.cafe24.shoppingmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shoppingmall.repository.UserDao;
import com.cafe24.shoppingmall.vo.UserVo;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;

	public boolean checkOverapId(String id) {

		String userId = userDao.selectUserByUserId(id);
		
		return null != userId;
	}

	public boolean join(UserVo userVo) {
		
		return 1 == userDao.insertUser(userVo);
	}
	
	public List<UserVo> getUserList() {
		return userDao.selectUserList();
	}
	
	public UserVo login(UserVo userVo) {
		
		return userDao.selectUserByUserIdAndPassword(userVo);
	}

}
