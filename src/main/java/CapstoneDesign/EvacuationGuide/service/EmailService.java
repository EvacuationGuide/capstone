package CapstoneDesign.EvacuationGuide.service;
import CapstoneDesign.EvacuationGuide.domain.Member;
import CapstoneDesign.EvacuationGuide.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    private final JavaMailSender emailSender;
    private final MemberRepository memberRepository;

    @Autowired
    public EmailService(JavaMailSender emailSender, MemberRepository memberRepository) {
        this.emailSender = emailSender;
        this.memberRepository = memberRepository;
    }



    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendEmailAll(String subject, String text) {
        //예시 위해 테스트 멤버 생성
        Member member1 = new Member("100", "wonjun99526@naver.com","testMember1",false);
        Member member2 = new Member("110", "wonjun99526@naver.com","testMember1",false);
        Member member3 = new Member("120", "wonjun99526@naver.com","testMember1",false);
        Member member4 = new Member("130", "wonjun995263@naver.com","testMember4",false);
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);

        //조회는 다른 멤버필드로 대체 가능. 단, 그러면 MemberRepository에 메소드 만들어야함
        List<Member> byNickname = memberRepository.findByNickname("testMember1");

        for (Member member : byNickname) {
            System.out.println("member.getId() = " + member.getId());
            System.out.println("member.getMail() = " + member.getMail());
            System.out.println("member.getNickname() = " + member.getNickname());
            System.out.println("member.getPassword() = " + member.getPassword());

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(member.getMail());
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
        }


    }
}
