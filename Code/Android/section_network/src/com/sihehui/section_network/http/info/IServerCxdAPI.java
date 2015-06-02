package com.sihehui.section_network.http.info;

import java.util.List;
import java.util.Map;

import com.sihehui.section_vo.vo.BannerVO;
import com.sihehui.section_vo.vo.DefaultVO;
import com.sihehui.section_vo.vo.LoanVO;
import com.sihehui.section_vo.vo.AuthVO;
import com.sihehui.section_vo.vo.UserLoginVO;

public interface IServerCxdAPI {

	<T> void getObjectData(Map<String, Object> input, String serviceName,
			IConnectionNetworkAble<T> connection, Class<T> objClass);

	<T> void getArrayData(Map<String, Object> input, String serviceName,
			IConnectionNetworkAble<List<T>> connection, Class<T> objeClass);

	// 获取首页投资项目
	void getLoanService(Map<String, Object> input,
			IConnectionNetworkAble<List<LoanVO>> connection);

	// 获取首页轮播图片
	void getBannerImage(Map<String, Object> input,
			IConnectionNetworkAble<List<BannerVO>> connection);

	// 获取认证码
	void getAuthCode(Map<String, Object> input,
			IConnectionNetworkAble<DefaultVO> connection);

	// 注册
	void registerService(Map<String, Object> input,
			IConnectionNetworkAble<DefaultVO> connection);

	// 登录

	void login(Map<String, Object> input,
			IConnectionNetworkAble<UserLoginVO> connection);

	// 重设密码
	void resetPsw(Map<String, Object> input,
			IConnectionNetworkAble<DefaultVO> connection);
	
	//实名认证
	void auth(Map<String, Object> input, String serviceName,
			IConnectionNetworkAble<AuthVO> connection);
}
