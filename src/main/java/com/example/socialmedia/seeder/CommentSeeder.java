package com.example.socialmedia.seeder;

import com.example.socialmedia.entity.Comment;
import com.example.socialmedia.entity.Post;
import com.example.socialmedia.entity.User;
import com.example.socialmedia.repository.CommentRepository;
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
@Log4j2
@AllArgsConstructor
@Component
@Order(3)
public class CommentSeeder implements CommandLineRunner {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public void run(String... args) {

        if (commentRepository.count() > 0) {
            log.info("CommentSeeder skipped (already has data)");
            return;
        }

        List<User> users = userRepository.findAll();
        List<Post> posts = postRepository.findAll();

        if (users.isEmpty() || posts.isEmpty()) {
            log.warn("Skip CommentSeeder: users or posts empty");
            return;
        }

        Random random = new Random();
        int length = random.nextInt(100, 150);

        String[] commentContents = {
                "Totally agree with you!",
                "This is amazing!",
                "Haha, so funny!",
                "Thanks for sharing this.",
                "I never thought about it that way.",
                "Can you explain more?",
                "Wow, impressive!",
                "This made my day!",
                "I have the same experience.",
                "Absolutely love this!",
                "So true!",
                "Interesting perspective.",
                "Great point!",
                "I need to try this.",
                "This is hilarious!",
                "Very helpful, thanks!",
                "I couldn't agree more.",
                "This is so relatable.",
                "Thanks for the info!",
                "Awesome content!",
                "I learned something new today.",
                "Mind blown!",
                "This is so cute!",
                "Love it!",
                "I need this in my life.",
                "Can't stop laughing!",
                "So inspiring!",
                "Amazing work!",
                "This is gold!",
                "I totally relate.",
                "Good job!",
                "Well explained!",
                "Super helpful, thanks!",
                "This is exactly what I needed.",
                "Nice one!",
                "Very insightful.",
                "I appreciate this post.",
                "This is fantastic!",
                "Such a great tip!",
                "Loved reading this!"
        };

        for (int i = 0; i < length; i++) {
            Comment comment = new Comment();
            comment.setUser(users.get(random.nextInt(users.size())));
            comment.setPost(posts.get(random.nextInt(posts.size())));
            comment.setContent(commentContents[random.nextInt(commentContents.length)]);
            comment.setCreatedAt(LocalDateTime.now());

            commentRepository.save(comment);
        }

        log.info("CommentSeeder added {} comments successfully", length);
    }
}

