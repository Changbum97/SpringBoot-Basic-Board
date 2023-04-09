package com.study.basicboard.repository;

import com.study.basicboard.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLoginId(String loginId);

    Boolean existsByLoginId(String loginId);
    Boolean existsByNickname(String nickname);
}
