package com.example.aboard.dao;

import com.example.aboard.entity.*;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MemberDao {
  @Insert("insert into member(username, password, email) values(#{username}, #{password}, #{email})")
  int insert(Member member);

  @Select("select * from member where username=#{username}")
  Member findByUsername(String username);

  @Update("update member set password=#{password} where username=#{username}")
  int updatePassword(String username, String password);
}
