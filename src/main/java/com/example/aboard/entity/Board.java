package com.example.aboard.entity;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.*;

// 글을 작성할 때는 title, content만 필수입력.
// 글을 변경할 때는 bno, title, content가 필수입력
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {
  @Setter
  private long bno;
  private String title;
  private String content;
  private String writer;
  private LocalDateTime writeTime;
  private long readCnt;
  private long goodCnt;

  public void increaseReadCnt() {
    this.readCnt++;
  }
}
