package com.example.aboard.controller;

import com.example.aboard.dto.*;
import com.example.aboard.service.*;
import jakarta.servlet.http.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.security.*;

@Controller
public class MemberController {
  @Autowired
  private MemberService memberService;

  @PreAuthorize("isAnonymous()")
  @GetMapping("/member/join")
  public void join() { }

  @PreAuthorize("isAnonymous()")
  @GetMapping("/member/login")
  public void login() {  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/member/check-password")
  public ModelAndView checkPassword(HttpSession session) {
    // 이미 비밀번호를 확인했다면 내정보보기로 보낸다
    if(session.getAttribute("checkPassword")!=null)
      return new ModelAndView("redirect:/member/readme");
    return new ModelAndView("member/check-password");
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/member/readme")
  public ModelAndView readme(HttpSession session, Principal principal) {
    // 비밀번호를 확인하지 않았아면 비밀번호로 확인으로 이동
    if(session.getAttribute("checkPassword")==null)
      return new ModelAndView("redirect:/member/check-password");
    MemberReadDto member = memberService.readme(principal.getName());
    return new ModelAndView("member/readme").addObject("member", member);
  }

  @PreAuthorize("isAnonymous()")
  @PostMapping("/member/join")
  public ModelAndView join(@ModelAttribute @Valid MemberJoinDto dto) {
    memberService.join(dto);
    return new ModelAndView("redirect:/member/login");
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/member/check-password")
  public ModelAndView checkPassword(@RequestParam @NotEmpty String password, Principal principal, HttpSession session) {
    boolean isSuccess = memberService.checkPassword(password, principal.getName());
    if(isSuccess==false)
      return new ModelAndView("redirect:/member/check-password?error");
    session.setAttribute("checkPassword", true);
    return new ModelAndView("redirect:/member/readme");
  }
}
