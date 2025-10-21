package com.example.aboard.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class BoardController {
  @GetMapping("/")
  public ModelAndView index() {
    return new ModelAndView("index");
  }
}
