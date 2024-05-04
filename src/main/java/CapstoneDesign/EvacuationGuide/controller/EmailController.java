package CapstoneDesign.EvacuationGuide.controller;

import CapstoneDesign.EvacuationGuide.repository.MemberRepository;
import CapstoneDesign.EvacuationGuide.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    //특정 멤버에게 메일 전송
    @GetMapping("/sendEmail")
    public String sendEmail(@RequestParam("to") String to) {
        emailService.sendEmail(to, "Test Email", "This is a test email from Spring Boot application.");
        return "mail/default";
    }

    //여러 멤버에게 메일 전송
    @GetMapping("/sendEmailAll")
    public String sendEmailAll() {
        emailService.sendEmailAll("Test Email", "This is a test email from Spring Boot application.");
        return "mail/default";
    }
}