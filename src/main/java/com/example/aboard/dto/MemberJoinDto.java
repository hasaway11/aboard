package com.example.aboard.dto;

import com.example.aboard.entity.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberJoinDto {
  @NotEmpty
  @Pattern(regexp="^[a-z0-9]{6,10}$")
  private String username;
  @NotEmpty
  @Pattern(regexp="^[A-Za-z0-9]{6,10}$")
  private String password;
  @NotEmpty
  @Email
  private String email;

  public Member toEntity(String encodedPassword) {
    return Member.builder().username(username).password(encodedPassword).email(email).build();
  }
}
