package CapstoneDesign.EvacuationGuide.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelter_id")
    private Long id;

    private String password;
    private String mail;
    private String nickname;

    private boolean pushAlarm;

    //ID는 @GerneratedValue로 자동 생성해주니, Id 필드 제외한 나머지 필드만으로 생성해주는 생성자
    public Member(String password, String mail, String nickname, boolean pushAlarm) {
        this.password = password;
        this.mail = mail;
        this.nickname = nickname;
        this.pushAlarm = pushAlarm;
    }
}