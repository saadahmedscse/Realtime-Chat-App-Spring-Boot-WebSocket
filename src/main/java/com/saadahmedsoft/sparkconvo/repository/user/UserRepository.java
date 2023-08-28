package com.saadahmedsoft.sparkconvo.repository.user;

import com.saadahmedsoft.sparkconvo.dto.user.UserProfileResponse;
import com.saadahmedsoft.sparkconvo.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    User findFirstByEmail(String email);

    @Query("SELECT NEW com.saadahmedsoft.sparkconvo.dto.user.UserProfileResponse(user.id, user.name, user.email, user.photo, user.gender, user.createdAt, user.updatedAt) FROM User user WHERE user.email <> :email")
    List<UserProfileResponse> getAllUserExceptMyself(@Param("email") String email);

    @Query("SELECT NEW com.saadahmedsoft.sparkconvo.dto.user.UserProfileResponse(user.id, user.name, user.email, user.photo, user.gender, user.createdAt, user.updatedAt) FROM User user WHERE user.email = :email")
    UserProfileResponse getProfile(@Param("email") String email);
}
