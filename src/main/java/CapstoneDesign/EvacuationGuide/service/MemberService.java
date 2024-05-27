package CapstoneDesign.EvacuationGuide.service;

import CapstoneDesign.EvacuationGuide.domain.Member;
import CapstoneDesign.EvacuationGuide.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public ResponseEntity<String> updateLocation(String mail, Double latitude, Double longitude) {

        Member member = memberRepository.findByMail(mail).get();
        if (member != null) {
            member.updateLocation(latitude, longitude);
            memberRepository.save(member);
            return ResponseEntity.ok("Location updated successfully");
        } else {
            return ResponseEntity.status(404).body("User not found");
        }

    }
}
