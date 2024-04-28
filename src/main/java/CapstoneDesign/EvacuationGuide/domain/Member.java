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

//    private Address address;
//    private Address work1;
//    private Address work2;
//    private Address work3;
}