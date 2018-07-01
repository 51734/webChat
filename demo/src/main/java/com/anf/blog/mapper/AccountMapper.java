package com.anf.blog.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.anf.blog.domain.Account;
import com.anf.blog.domain.ChatGroup;
import com.anf.blog.domain.User;


public interface AccountMapper {
	
	@Select("select * from account where email=#{email} and password=#{password}")
	List<Account> accontLogin(@Param("email")String email,@Param("password")String password);
	
	@Select("select max(userid)+1 from account")
	int getId();
	
	@Insert("insert into account (userid,email,username,password) values(#{userid},#{email},#{username},#{password})")
	void userInsert(Account accountInfo);
	
	@Insert("insert into user(userid,username,createtime) values(#{userid},#{username},sysdate())")
	void personalInsert(Account accountInfo);
	
	@Select("select * from user where userid=#{userid}")
	List<User> getPerson(@Param("userid")int userid);

	@Select("select * from user u "
			+ "left join userGroup ug on u.userid=ug.userid "
			+ "where ug.groupid=#{groupid}")
	List<User> getFriends(@Param("groupid")int groupid);

	@Select("select cg.groupid groupid,cg.groupname groupname from chatgroup cg "
			+ "where cg.createuser=#{userid} and cg.grouptype='f'")
	List<Map<String, Object>> getGroupName(int userid);

	@Select("select * from chatgroup cg "
			+ "left join usergroup ug on cg.groupid = ug.groupid "
			+ "where ug.userid=#{userid} and cg.grouptype='g'")
	List<ChatGroup> getGroups(int userid);

	@Select("select * from user where username like #{key} or city like #{key} order by id limit #{page},6")
	List<User> getUser(@Param("key")String key,@Param("page")int page);
	
	@Select("select count(1) from user where username like #{key} or city like #{key} or sign like #{key}")
	long findUserCount(@Param("key")String key);
	
	
}
