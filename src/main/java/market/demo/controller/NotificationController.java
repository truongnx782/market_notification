package market.demo.controller;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import market.demo.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {
    private final NotificationService notificationService;

//    @PostMapping("/send-otp-register")
//    public void sendOtpRegister(@RequestBody Map<String,Object> objectMap) throws MessagingException {
//       notificationService.sendOtpRegister(objectMap);
//    }

//    @PostMapping("/send-otp-forgot-password")
//    public void sendOtpForgotPassword(@RequestBody Map<String,Object> objectMap) throws MessagingException {
//        notificationService.sendOtpForgotPassword(objectMap);
//    }

    @KafkaListener(topics = "mail-register-topic", groupId = "auth-group")
    public void listenForMailRegister(Map<String, Object> objectMap) throws MessagingException {
        notificationService.sendOtpRegister(objectMap);
    }

    @KafkaListener(topics = "mail-forgot-password-topic", groupId = "auth-group")
    public void sendOtpForgotPassword(Map<String, Object> objectMap) throws MessagingException {
        notificationService.sendOtpForgotPassword(objectMap);
    }

    @KafkaListener(topics = "mail-change-status-post-topic", groupId = "trade-group")
    public void sendChangeStatusPost(Map<String,Object> userMaper) throws MessagingException {
        notificationService.sendChangeStatusPost(userMaper);
    }

}
