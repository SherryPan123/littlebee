package cn.edu.dhu.library.littlebee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing
public class LittlebeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LittlebeeApplication.class, args);
    }
}
