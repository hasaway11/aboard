package com.example.aboard.controller;

import com.example.aboard.dto.*;
import com.example.aboard.service.*;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class BoardController {
  @Autowired
  private BoardService boardService;

  @GetMapping("/")
  public ModelAndView index(@RequestParam(defaultValue="1") long pageno) {
    BoardPageDto page = boardService.findAll(pageno);
    return new ModelAndView("index").addObject("page", page);
  }
}
