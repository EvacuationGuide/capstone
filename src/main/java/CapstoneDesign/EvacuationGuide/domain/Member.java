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

    private Double latitude;
    private Double longitude;

    public Member(String password, String mail, String nickname, boolean pushAlarm) {
        this.password = password;
        this.mail = mail;
        this.nickname = nickname;
        this.pushAlarm = pushAlarm;
    }

    public void updateLocation(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}