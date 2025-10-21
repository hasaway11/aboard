package com.example.aboard.dto;

import lombok.*;

import java.time.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberReadDto {
  private String email;
  private LocalDate joinDay;
  private long days;
  private String role;
}
