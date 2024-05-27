package CapstoneDesign.EvacuationGuide.controller;

import CapstoneDesign.EvacuationGuide.domain.Member;
import CapstoneDesign.EvacuationGuide.repository.MemberRepository;
import CapstoneDesign.EvacuationGuide.DTO.KakaoInfo;
import CapstoneDesign.EvacuationGuide.service.KakaoService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Controller
public class KakaoController {



    @Autowired
    KakaoService ks;

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/member/do")
    public String loginPage()
    {
        return "kakaoCI/login";
    }


    @GetMapping("/member/kakao")
    public String getCI(@RequestParam String code, HttpSession session) throws IOException {
        String access_Token = null;
        try {
            access_Token = ks.getToken(code);
        }catch (JsonParseException e){
            throw new RuntimeException(e);
        }

        KakaoInfo kakaoInfo = null;
        try {
            kakaoInfo = ks.getKakaoInfo(access_Token);
        }catch (JsonParseException e){
            throw new RuntimeException(e);
        }
        System.out.println("kakaoInfo.getEmail() = " + kakaoInfo.getMail());

        Optional<Member> kakaoMember = ks.ifNeedKakaoInfo(kakaoInfo);
        log.info("로그인 유저 = {}",kakaoMember);


        if(kakaoMember.isPresent()){
            Member member = kakaoMember.get();

            session.setAttribute("loginMember", member);
            // session.setMaxInactiveInterval( ) : 세션 타임아웃을 설정하는 메서드
            session.setMaxInactiveInterval(60*30);
            // 로그아웃 시 사용할 카카오토큰 추가
            session.setAttribute("kakaoToken", access_Token);

            System.out.println("세션 설정 완료");
        }

        return "redirect:/";

    }

    @GetMapping("/oauth/kakao/logout")
    public String kakaoLogout(HttpSession session) {
        String accessToken = (String) session.getAttribute("kakaoToken");

        if(accessToken != null && !"".equals(accessToken)){
            try {
                ks.kakaoDisconnect(accessToken);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            session.removeAttribute("kakaoToken");
            session.removeAttribute("loginMember");
        }else{
            System.out.println("accessToken is null");
        }

        return "redirect:/";
    }

}