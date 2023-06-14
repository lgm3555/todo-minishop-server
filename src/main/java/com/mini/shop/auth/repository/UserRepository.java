package com.mini.shop.auth.repository;

import com.mini.shop.auth.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
    boolean existsByUsername(String username);
    Member findByUsernameAndNickname(String username, String nickname);
}
