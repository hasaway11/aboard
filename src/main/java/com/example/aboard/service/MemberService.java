package com.example.aboard.service;

import com.example.aboard.dao.*;
import com.example.aboard.dto.*;
import com.example.aboard.entity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

@Service
public class MemberService {
  @Autowired
  private MemberDao memberDao;
  @Autowired
  private PasswordEncoder encoder;

  // 가입
  public boolean join(MemberJoinDto dto) {
    String encodedPassword = encoder.encode(dto.getPassword());
    Member member = dto.toEntity(encodedPassword);
    return memberDao.insert(member)==1;
  }

  // 비밀번호 확인
  public boolean checkPassword(String password, String loginId) {
    Member member = memberDao.findByUsername(loginId);
    return encoder.matches(password, member.getPassword());
  }

  // 내정보 보기
  public MemberReadDto readme(String loginId) {
    Member member = memberDao.findByUsername(loginId);
    return member.toReadDto();
  }

  // 비밀번호 변경
  public boolean changePassword(MemberChangePasswordDto dto, String loginId) {
    Member member = memberDao.findByUsername(loginId);
    if(encoder.matches(dto.getCurrentPassword(), member.getPassword())==false)
      return false;
    String newEncodedPassword = encoder.encode(dto.getNewPassword());
    return memberDao.updatePassword(loginId, newEncodedPassword)==1;
  }
}
