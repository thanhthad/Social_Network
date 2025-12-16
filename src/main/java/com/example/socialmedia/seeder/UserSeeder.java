package com.example.socialmedia.seeder;

import com.example.socialmedia.entity.User;
import com.example.socialmedia.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
@AllArgsConstructor
@Log4j2
@Order(1)
public class UserSeeder implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count() == 0){
            Random random = new Random();
            int length = random.nextInt(60,200);
            for(int i = 1 ; i <= length ;i++){
                User user = new User();
                user.setUsername("User" + i);
                user.setPassword("Password" + i);
                user.setEmail("tauhaitac"+i+"k@gmail.com");
                user.setCreatedAt(LocalDateTime.now());
                userRepository.save(user);
            }
            log.info("Seeder: Add sucesfully user");
        }
        else{
            log.warn("Add fail user");
        }
    }
}
