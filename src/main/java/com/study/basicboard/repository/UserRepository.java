package com.study.basicboard.repository;

import com.study.basicboard.domain.entity.User;
import com.study.basicboard.domain.enum_class.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLoginId(String loginId);
    Page<User> findAllByNicknameContains(String nickname, PageRequest pageRequest);
    Boolean existsByLoginId(String loginId);
    Boolean existsByNickname(String nickname);
    Long countAllByUserRole(UserRole userRole);

}
