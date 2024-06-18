package CapstoneDesign.EvacuationGuide.controller;

import CapstoneDesign.EvacuationGuide.domain.Member;
import CapstoneDesign.EvacuationGuide.repository.MemberRepository;
import CapstoneDesign.EvacuationGuide.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @PostMapping("/updateLocation")
    public ResponseEntity<String> updateLocation(@RequestParam String mail, @RequestParam Double latitude, @RequestParam Double longitude) {
        Member member = memberRepository.findByMail(mail).get();
        if (member != null) {
            memberService.updateLocation(mail, latitude, longitude);
            Member savedMember = memberRepository.save(member);

            log.info("latitude={}",savedMember.getLatitude());
            log.info("longitude={}",savedMember.getLongitude());
            return ResponseEntity.ok("Location updated successfully");
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

}
