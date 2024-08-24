package market.demo.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final Map<String, String> otpCache = new ConcurrentHashMap<>();
    private final JavaMailSender javaMailSender;

    public void sendOtpRegister(Map<String, Object> objectMap) throws MessagingException {
        String email = (String) objectMap.get("email");
        String password = (String) objectMap.get("password");
        int randomNumber = (int) objectMap.get("randomNumber");


        // Xây dựng URL với các tham số
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8888/market_auth/auth/confirm-register")
                .queryParam("email", email)
                .queryParam("password", password)
                .queryParam("otp", randomNumber)
                .toUriString();

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(email);
        helper.setSubject("Xác nhận đăng kí tài khảon");

        // HTML content with the icon as a clickable link
        String htmlContent = "<html>"
                + "<body>"
                + "<p style=\"display: inline; margin-right: 10px;\">XÁC NHẬN:</p>"
                + "<a href=\"" + url + "\" style=\"display: inline;\">"
                + "<img src=\"https://media.istockphoto.com/id/1080145334/vector/icon-quality.jpg?s=612x612&w=0&k=20&c=8BbbL_TXaH0oxnjiqWKrQSiqUlBC1S4E9HCRc1ZtNGs=\" alt=\"Confirm Password\" style=\"width:50px;height:50px;\">"
                + "</a>"
                + "</body>"
                + "</html>";

        helper.setText(htmlContent, true);
        javaMailSender.send(message);
    }

    public void sendOtpForgotPassword(Map<String, Object> objectMap) throws MessagingException {
        String email = (String) objectMap.get("email");
        String password = (String) objectMap.get("password");
        int randomNumber = (int) objectMap.get("randomNumber");


        // Xây dựng URL với các tham số
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8888/market_auth/auth/confirm-forgot-password")
                .queryParam("email", email)
                .queryParam("password", password)
                .queryParam("otp", randomNumber)
                .toUriString();

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(email);
        helper.setSubject("Xác nhận đổi mật khẩu mới");

        // HTML content with the icon as a clickable link
        String htmlContent = "<html>"
                + "<body>"
                + "<p style=\"display: inline; margin-right: 10px;\">XÁC NHẬN:</p>"
                + "<a href=\"" + url + "\" style=\"display: inline;\">"
                + "<img src=\"https://media.istockphoto.com/id/1080145334/vector/icon-quality.jpg?s=612x612&w=0&k=20&c=8BbbL_TXaH0oxnjiqWKrQSiqUlBC1S4E9HCRc1ZtNGs=\" alt=\"Confirm Password\" style=\"width:50px;height:50px;\">"
                + "</a>"
                + "</body>"
                + "</html>";

        helper.setText(htmlContent, true);
        javaMailSender.send(message);
    }


}
