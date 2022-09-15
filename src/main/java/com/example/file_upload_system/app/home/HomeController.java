package com.example.file_upload_system.app.home;

import com.example.file_upload_system.app.member.entity.Member;
import com.example.file_upload_system.app.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    @GetMapping("/")
    public String showMain(Principal principal, Model model) {

        return "home/main";
    }

    @GetMapping("/about")
    public String showAbout(Principal principal, Model model) {
        return "home/about";
    }
}