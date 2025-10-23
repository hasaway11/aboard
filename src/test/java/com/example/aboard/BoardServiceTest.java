package com.example.aboard;

import com.example.aboard.service.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

@SpringBootTest
public class BoardServiceTest {
  @Autowired
  private BoardService boardService;

  @Test
  public void pagingTest() {
    for(int pageno=1; pageno<=13; pageno++) {
      boardService.findAll(pageno);
    }
  }
}
