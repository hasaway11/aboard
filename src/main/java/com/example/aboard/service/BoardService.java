package com.example.aboard.service;

import com.example.aboard.dao.*;
import com.example.aboard.dto.*;
import com.example.aboard.entity.*;
import jakarta.validation.constraints.*;
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

  // 조회수 증가 : 로그인 and 글쓴이가 아니다
  public Board findByBno(Long bno, String loginId) {
    Board board = boardDao.findByBno(bno);
    if(loginId!=null && board.getWriter().equals(loginId)==false) {
      boardDao.increaseReadCnt(bno);
      board.increaseReadCnt();
    }
    return board;
  }

  public void delete(Long bno, String loginId) {
    String writer = boardDao.findWriterByBno(bno);
    if(writer.equals(loginId))
      boardDao.delete(bno);
  }

  public long insert(String title, String content, String loginId) {
    // DAO에서 insert할 때 board_seq.nextval 시퀀스 값으로 bno가 채워진다
    //  - 서비스에서 그 bno값을 리턴하려면(...접근하려면)
    //  - 1. DAO에서 시퀀스를 리턴하자 -> NO!. 마이바티스 insert, delete, update의 리턴값은 "변경된 행의 개수"
    //  - 2. 마이바티스 SelectKey
    Board board = Board.builder().title(title).content(content).writer(loginId).build();
    boardDao.insert(board);
    return board.getBno();
  }
}









