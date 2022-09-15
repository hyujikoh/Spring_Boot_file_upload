package com.example.file_upload_system.app.home;

import com.example.file_upload_system.app.member.entity.Member;
import com.example.file_upload_system.app.member.service.MemberService;
import com.example.file_upload_system.app.security.dto.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    @GetMapping("/")
    public String showMain() {

        return "home/main";
    }

    @GetMapping("/about")
    public String showAbout() {
        return "home/about";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/currentUserOrigin")
    @ResponseBody
    public Principal currentUserOrigin(Principal principal) {
        return principal;
    }

    @GetMapping("/currentUser")
    @ResponseBody // 로그인 한 객체
    public MemberContext currentUser(@AuthenticationPrincipal MemberContext memberContext) {
        return memberContext;
    }
}