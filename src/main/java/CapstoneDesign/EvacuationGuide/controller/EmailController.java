package CapstoneDesign.EvacuationGuide.controller;

import CapstoneDesign.EvacuationGuide.repository.MemberRepository;
import CapstoneDesign.EvacuationGuide.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {

    MemberRepository memberRepository;


    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/sendEmail")
    public String sendEmail(@RequestParam String to) {
        emailService.sendEmail(to, "Test Email", "This is a test email from Spring Boot application.");
        return "Email sent successfully!";
    }
}