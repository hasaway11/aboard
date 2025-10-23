package com.example.aboard.dto;

import com.example.aboard.entity.*;
import lombok.*;

import java.util.*;

// pagination 정보 : pageno, prev, next, ArrayList
// List<Board>
@Data
@AllArgsConstructor
public class BoardPageDto {
  private List<Board> boards;
  private long pageno;
  private long prev;
  private long next;
  private List<Long> pagenos;
}
