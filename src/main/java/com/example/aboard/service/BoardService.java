package com.example.aboard.service;

import com.example.aboard.dao.*;
import com.example.aboard.dto.*;
import com.example.aboard.entity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class BoardService {
  @Autowired
  private BoardDao boardDao;
  private final long 페이지당_글의_개수 = 10;
  private final long 블록당_페이지수 = 5;

  public BoardPageDto findAll(long pageno) {
    /*
      글의 개수가 123개라고 하자. 그러면 페이지는 1~13페이지까지.

      pageno        prev    start       end       next
        1~5           0       1         5           6
        6~10          5       6         10          11
       11~13         10       11        13          0
    */
    long totalcount = boardDao.count();

    // 원하는 페이지의 개수  : 글의 개수 119->12p, 120->12p, 121->13p
    // totalcount/10 + 1  : 글의 개수 119->12p, 120->13p, 121->13p
    long numberOfPages = (totalcount-1)/페이지당_글의_개수 +1 ;

    // 1-5 -> 0*5           6-10 -> 1*5        11-13 - > 2*5
    long prev = (pageno-1)/블록당_페이지수 * 블록당_페이지수;
    long start = prev + 1;
    long end = prev + 블록당_페이지수;
    long next = end + 1;

    // 위처럼 계산하면 11~13페이지일때 end가 15, next가 16이 나온다
    // 현재 페이지의 개수 13이므로 end는 13, next는 0으로 바꿔준다
    if(end>=numberOfPages) {
      end = numberOfPages;
      next = 0;
    }


    // pageno 1   0~10,   pageno 2  10~20
    long startRownum = (pageno-1) * 블록당_페이지수;
    long endRownum = startRownum + 페이지당_글의_개수;
    List<Board> boards = boardDao.findByPageno(startRownum, endRownum);

    List<Long> pagenos = new ArrayList<>();
    for(long i=start; i<=end; i++)
      pagenos.add(i);

    return new BoardPageDto(boards, pageno, prev, next, pagenos);
  }
}
