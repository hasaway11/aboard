package com.example.aboard.service;

import com.example.aboard.dao.*;
import com.example.aboard.entity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

@Service
public class ABoardUserDetailsService implements UserDetailsService {
  @Autowired
  private MemberDao memberDao;

  // DB에서 사용자 정보를 읽어 UserDetails(아이디, 비밀번호, role + 알파)로 변환 후 리턴한다
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Member m = memberDao.findByUsername(username);
    return User.builder().username(m.getUsername()).password(m.getPassword()).roles(m.getRole()).build();
  }
}
