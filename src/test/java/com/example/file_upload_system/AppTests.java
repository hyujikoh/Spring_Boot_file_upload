package com.example.file_upload_system;

import com.example.file_upload_system.app.home.HomeController;
import com.example.file_upload_system.app.member.controller.MemberController;
import com.example.file_upload_system.app.member.entity.Member;
import com.example.file_upload_system.app.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.core.io.Resource;
import javax.transaction.Transactional;

import java.io.InputStream;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
@SpringBootTest
@AutoConfigureMockMvc
@Transactional // 테스트 환경에서 해당 어노테이션을 수행하면 수행된건 실제 디비에 반영 안되게 할것이다

@ActiveProfiles("test")
public class AppTests {

    @Autowired
    private MockMvc mvc; // 컨트롤러 테스트
    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("메인화면에서는 안녕이 나와야 한다.")
    void t1() throws Exception {
        // WHEN
        // GET /
        ResultActions resultActions = mvc
                .perform(get("/"))
                .andDo(print()); // 요청이 날라오면 화면에 출력해라

        // THEN
        // 안녕
        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(handler().handlerType(HomeController.class))
                .andExpect(handler().methodName("showMain"))
                .andExpect(content().string(containsString("안녕")));
    }

    @Test
    @DisplayName("회원의 수")
    @Rollback(false)
    void t2() throws Exception {
        long count = memberService.count();
        assertThat(count).isGreaterThan(5); // 0명이상
    }


    @Test
    @DisplayName("user1로 로그인 후 프로필페이지에 접속하면 user1의 이메일이 보여야 한다.")
    @WithUserDetails("user1")
    void t3() throws Exception {
        ResultActions resultActions = mvc.perform(get("/member/profile").with(user("user1").password("1234").roles("user"))).andDo(print());


        // mockMvc로 로그인 처리
        resultActions
                .andExpect(status().is2xxSuccessful()).andExpect(handler().handlerType(MemberController.class)).andExpect(handler().methodName("showProfile")).andExpect(content().string(containsString("user1@test.com")));
    }

    @Test
    @DisplayName("user4로 로그인 후 프로필페이지에 접속하면 user4의 이메일이 보여야 한다.")
    @Rollback(false)
    void t4() throws Exception {
        // WHEN
        // GET /
        ResultActions resultActions = mvc
                .perform(
                        get("/member/profile")
                                .with(user("user4").password("1234").roles("user"))
                )
                .andDo(print());

        // THEN
        // 안녕
        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("showProfile"))
                .andExpect(content().string(containsString("user4@test.com")));
    }
    @Test
    @DisplayName("회원가입")

    void t5() throws Exception {
        String testUploadFileUrl = "https://picsum.photos/200/300";
        String originalFileName = "test.png";

        // wget , 파일 다운로드 위해
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Resource> response = restTemplate.getForEntity(testUploadFileUrl, Resource.class);
        InputStream inputStream = response.getBody().getInputStream();

        MockMultipartFile profileImg = new MockMultipartFile(
                "profileImg",
                originalFileName,
                "image/png",
                inputStream
        );

        // 회원가입(MVC MOCK)
        // when
        ResultActions resultActions = mvc.perform(
                        multipart("/member/join")
                                .file(profileImg)
                                .param("username", "user5")
                                .param("password", "1234")
                                .param("email", "user5@test.com")
                                .characterEncoding("UTF-8"))
                .andDo(print());

        resultActions
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/member/profile"))
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("join"));

        Member member = memberService.getMemberById(5L);

        assertThat(member).isNotNull();

        memberService.removeProfileImg(member);

        // 5번회원이 생성되어야 함, 테스트
        // 여기 마저 구현

        // 5번회원의 프로필 이미지 제거
    }

}
