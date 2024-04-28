package CapstoneDesign.EvacuationGuide.DTO;

import CapstoneDesign.EvacuationGuide.domain.Address;
import CapstoneDesign.EvacuationGuide.domain.Member;
import lombok.*;

@Data
@NoArgsConstructor
public class MemberDTO {

    private String password;
    private String mail;
    private String nickname;

    // private Address address;

    @Builder
    public MemberDTO(String password, String mail, String nickname){
        this.password = password;
        this.mail = mail;
        this.nickname = nickname;
    }

    public Member toEntity(){
        return Member.builder()
                .password(password)
                .mail(mail)
                .nickname(nickname)
                .build();
    }

}
