package CapstoneDesign.EvacuationGuide.service;
import CapstoneDesign.EvacuationGuide.domain.Member;
import CapstoneDesign.EvacuationGuide.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailService {

    private final JavaMailSender emailSender;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendEmailEveryone(String subject, String text) {
        Member member1 = new Member(10L, "100", "wonjun99526@naver.com","testMmeber1",false);
        Member member2 = new Member(11L, "110", "wonjun995261@naver.com","testMmeber2",false);
        Member member3 = new Member(12L, "120", "wonjun995262@naver.com","testMmeber3",false);
        Member member4 = new Member(13L, "130", "wonjun995263@naver.com","testMmeber4",false);
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);
        Optional<Member> byMail = memberRepository.findByMail("wonjun99526@naver.com");


            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(byMail.get().getMail());
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);

    }
}
