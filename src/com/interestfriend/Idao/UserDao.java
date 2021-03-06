package com.interestfriend.Idao;

import java.sql.ResultSet;

import com.interestfriend.bean.User;

public interface UserDao {
	boolean insertUserToDB(User user);// 向数据库里插入一条用户数据

	boolean verifyCellphone(String cellPhone);// 验证手机号是否存在

	int userLogon(String telPhone, String password);

	ResultSet getUserInfo(int user_id);

	boolean updateUserInfo(int user_id, String column, String value);

	boolean updateUserName(int user_id, String name, String user_sort_key,
			String user_pinyin);

	boolean updateUserAvatar(User user);

	String[] getUserNameAndAvatar(int user_id);

	boolean updataLoginPassword(String cell_phone, String password);

	String findUserChatIDByUserID(int user_id);

	boolean updateUserAddress(int user_id, String address, String province,
			String province_key);
}
