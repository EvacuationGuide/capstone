package CapstoneDesign.EvacuationGuide.DTO;

import CapstoneDesign.EvacuationGuide.domain.Address;
import CapstoneDesign.EvacuationGuide.domain.Member;
import lombok.*;

@Data
@NoArgsConstructor
public class MemberDTO {

    private String mail;
    private String nickname;


    @Builder
    public MemberDTO(String mail, String nickname){
        this.mail = mail;
        this.nickname = nickname;
    }

    public Member toEntity(){
        return Member.builder()
                .mail(mail)
                .nickname(nickname)
                .build();
    }

}
