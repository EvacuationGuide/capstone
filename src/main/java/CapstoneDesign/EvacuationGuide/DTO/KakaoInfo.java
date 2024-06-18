package CapstoneDesign.EvacuationGuide.DTO;

import CapstoneDesign.EvacuationGuide.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoInfo {
    private Long id;
    private String nickname;
    private String mail;

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .nickname(nickname)
                .mail(mail)
                .build();
    }
}