package com.example.socialmedia.repository;

import com.example.socialmedia.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {
}
