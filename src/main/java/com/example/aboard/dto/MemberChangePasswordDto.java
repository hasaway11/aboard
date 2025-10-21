package com.example.aboard.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberChangePasswordDto {
  @NotEmpty
  @Pattern(regexp="^[A-Za-z0-9]{6,10}$")
  private String currentPassword;
  @NotEmpty
  @Pattern(regexp="^[A-Za-z0-9]{6,10}$")
  private String newPassword;
}
