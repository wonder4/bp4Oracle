package com.ebiz.bp_oracle.service;

import java.util.List;

import com.ebiz.bp_oracle.domain.UserInfo;

public interface UserInfoService {

	Long createUserInfo(UserInfo t);

	int modifyUserInfo(UserInfo t);

	int removeUserInfo(UserInfo t);

	UserInfo getUserInfo(UserInfo t);

	List<UserInfo> getUserInfoList(UserInfo t);

	Long getUserInfoCount(UserInfo t);

	List<UserInfo> getUserInfoPaginatedList(UserInfo t);

}
