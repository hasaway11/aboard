package com.example.aboard.controller;

import com.example.aboard.dto.*;
import com.example.aboard.entity.*;
import com.example.aboard.service.*;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.security.*;

@Controller
public class BoardController {
  @Autowired
  private BoardService boardService;

  @GetMapping("/")
  public ModelAndView index(@RequestParam(defaultValue="1") Long pageno) {
    BoardPageDto page = boardService.findAll(pageno);
    return new ModelAndView("index").addObject("page", page);
  }

  // 조회수 증가 : 자신의 쓴 글이면 조회수 증가 X, 내가 쓴 글이 아니면 조회수 증가
  @GetMapping("/board/read")
  public ModelAndView read(@RequestParam @NotNull Long bno, Principal principal) {
    String loginId = principal==null? null: principal.getName();
    Board board = boardService.findByBno(bno, loginId);
    return new ModelAndView("board/read").addObject("board", board)
      .addObject("isLogin", loginId!=null)
      .addObject("isWriter", board.getWriter().equals(loginId));
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/board/delete")
  public ModelAndView delete(@RequestParam @NotNull Long bno, Principal principal) {
    boardService.delete(bno, principal.getName());
    return new ModelAndView("redirect:/");
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/board/write")
  public void write() { }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/board/write")
  public ModelAndView write(@RequestParam @NotEmpty String title, @RequestParam @NotEmpty String content,
                            Principal principal) {
    long bno = boardService.insert(title, content, principal.getName());
    return new ModelAndView("redirect:/board/read?bno=" + bno);
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/board/update")
  public ModelAndView update(@RequestParam @NotEmpty Long bno) {
    Board board = boardService.findByBno(bno, null);
    return new ModelAndView("board/update").addObject("board", board);
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/board/update")
  public ModelAndView update(@RequestParam @NotEmpty String title, @RequestParam @NotEmpty String content,
                            @RequestParam @NotNull Long bno,  Principal principal) {
    //boardService.update(title, content, bno, principal.getName());
    return new ModelAndView("redirect:/board/read?bno=" + bno);
  }
}











