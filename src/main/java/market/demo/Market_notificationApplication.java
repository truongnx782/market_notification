package market.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Market_notificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(Market_notificationApplication.class, args);
    }

}
