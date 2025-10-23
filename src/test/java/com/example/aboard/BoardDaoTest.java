package com.example.aboard;

import com.example.aboard.dao.*;
import com.example.aboard.entity.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.util.*;

@SpringBootTest
public class BoardDaoTest {
  @Autowired
  private BoardDao boardDao;

//  @Test
//  public void insertAll() {
//    List<String> writers = Arrays.asList("spring","summer","winter");
//    for(int i=2; i<123; i++) {
//      Board board = Board.builder().title(i+"번째 글").content("내용없음")
//          .writer(writers.get(i%3)).build();
//      boardDao.insert(board);
//    }
//  }

  @Test
  public void findByPageno() {
    List<Board> boards = boardDao.findByPageno(11,20);
    boards.forEach(board-> System.out.println(board.getBno()));
  }
}


