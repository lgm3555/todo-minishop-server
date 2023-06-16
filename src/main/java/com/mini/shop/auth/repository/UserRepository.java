package com.mini.shop.auth.repository;

import com.mini.shop.auth.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(String id);
    boolean existsById(String id);
    Member findByIdAndEmail(String id, String email);
}
