package com.example.socialmedia.seeder;

import com.example.socialmedia.entity.Post;
import com.example.socialmedia.entity.User;
import com.example.socialmedia.repository.PostRepository;
import com.example.socialmedia.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Component
@AllArgsConstructor
@Log4j2
@Order(2)
public class PostSeeder implements CommandLineRunner {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if(postRepository.count() == 0){
            List<User> users = userRepository.findAll();
            Random random = new Random();
            int length = random.nextInt(80,100);
            String[] postContents = {
                    "Just had the best coffee of my life!",
                    "Loving the new season of my favorite show.",
                    "Can't believe how beautiful the sunset is today.",
                    "Reading a great book on productivity. Highly recommend!",
                    "Workout completed! Feeling energized.",
                    "Cooking a new recipe tonight. Fingers crossed!",
                    "Visited the park and enjoyed the fresh air.",
                    "Started learning Spring Boot, excited for backend dev!",
                    "Anyone has tips for staying motivated at work?",
                    "Just finished a long run, time for some rest.",
                    "Trying meditation for the first time, feeling calm.",
                    "My cat just did the funniest thing!",
                    "Working on a new side project, coding all night.",
                    "Went hiking this weekend, nature is amazing.",
                    "Experimenting with photography, love capturing moments.",
                    "Movie night with friends, popcorn ready!",
                    "Had a great conversation with an old friend today.",
                    "Planning a small trip next month, need suggestions.",
                    "Listening to some relaxing music while coding.",
                    "Celebrating small wins today, grateful for everything."
            };
            int length_post = postContents.length;
            for(int i = 1; i < length ; i++){
                Post post = new Post();
                post.setUser(users.get(random.nextInt(users.size() )));
                int posts = random.nextInt(length_post);
                post.setContent(postContents[posts]);
                post.setCreatedAt(LocalDateTime.now());
                postRepository.save(post);
            }
            log.info("Adding post's datas sucesfully");
        }else{
            log.warn("Your database has already had posts");
        }
    }
}
