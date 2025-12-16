package com.example.socialmedia.repository;

import com.example.socialmedia.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    // 1️⃣ Lấy tất cả users (pagination + sort)
    Page<User> findAll(Pageable pageable);

    // 2️⃣ Lọc theo khoảng thời gian createdAt
    Page<User> findByCreatedAtBetween(LocalDateTime start,
                                      LocalDateTime end,
                                      Pageable pageable);

    // 3️⃣ Tìm username chứa keyword (ignore case)
    Page<User> findByUsernameContainingIgnoreCase(String keyword,
                                                  Pageable pageable);

    // 4️⃣ Kết hợp username + khoảng thời gian
    Page<User> findByUsernameContainingIgnoreCaseAndCreatedAtBetween(
            String keyword,
            LocalDateTime start,
            LocalDateTime end,
            Pageable pageable);

    // 5️⃣ Lọc email kết thúc bằng domain
    Page<User> findByEmailEndingWith(String domain,
                                     Pageable pageable);

    // 6️⃣ Lọc username bắt đầu bằng prefix
    Page<User> findByUsernameStartingWith(String prefix,
                                          Pageable pageable);

    // 7️⃣ Lọc username kết thúc bằng suffix
    Page<User> findByUsernameEndingWith(String suffix,
                                        Pageable pageable);

    // 8️⃣ Lọc nhiều username (IN)
    Page<User> findByUsernameIn(List<String> usernames,
                                Pageable pageable);

    // 9️⃣ Lọc username không chứa từ nào đó (NOT LIKE)
    Page<User> findByUsernameNotContaining(String keyword,
                                           Pageable pageable);

    // 🔟 Sắp xếp theo createdAt giảm dần
    Page<User> findAllByOrderByCreatedAtDesc(Pageable pageable);

    // 1️⃣1️⃣ Sắp xếp theo createdAt tăng dần
    Page<User> findAllByOrderByCreatedAtAsc(Pageable pageable);
}
