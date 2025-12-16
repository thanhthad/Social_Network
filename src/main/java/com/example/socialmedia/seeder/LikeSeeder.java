package com.example.socialmedia.seeder;

import com.example.socialmedia.entity.Like;
import com.example.socialmedia.entity.Post;
import com.example.socialmedia.entity.User;
import com.example.socialmedia.repository.LikeRepository;
import com.example.socialmedia.repository.PostRepository;
import com.example.socialmedia.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@AllArgsConstructor
@Component
@Log4j2
@Order(4)
public class LikeSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    @Override
    public void run(String... args) throws Exception {
        if(likeRepository.count() == 0){
            List<User> users = userRepository.findAll();
            List<Post> posts = postRepository.findAll();
            Random random = new Random();
            int length = random.nextInt(150,200);
            Set<String> sets = new HashSet<>();
            for(int i = 0 ; i < length ; i ++){
                User user = users.get(random.nextInt(users.size() ));
                Post post = posts.get(random.nextInt(posts.size() ));

                String unique = user.getId() + "-" + post.getId();
                if (sets.contains(unique)){
                    continue;
                }
                sets.add(unique);
                Like like = new Like();
                like.setCreatedAt(LocalDateTime.now());
                like.setUser(user);
                like.setPost(post);
                likeRepository.save(like);
            }
            log.info("Add like's data sucesfully");
        }else{
            log.warn("Cannot add like's datas");
        }
    }
}
