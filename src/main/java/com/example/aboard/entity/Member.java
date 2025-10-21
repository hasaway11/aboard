package com.example.aboard.entity;

// 엔티티 : DB 테이블과 동일한 클래스(DB와 자바를 연결)
// DTO : 사용자와 작업하는 클래스(입력, 출력)

import com.example.aboard.dto.*;
import lombok.*;

import java.time.*;
import java.time.temporal.*;

@Getter
@Builder
public class Member {
  private String username;
  private String password;
  private String email;
  private LocalDate joinDay;
  private String role;

  public MemberReadDto toReadDto() {
    long days = ChronoUnit.DAYS.between(joinDay, LocalDate.now());
    return new MemberReadDto(email, joinDay, days, role);
  }
}
