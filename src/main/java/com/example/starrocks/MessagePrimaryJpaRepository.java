package com.example.starrocks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagePrimaryJpaRepository extends JpaRepository<MessagePrimaryEntity, Long> {

    @Query(value = """
    SELECT user_id, user_service, item_id, item_service, message_id, time
    FROM message_primary
    WHERE user_id = :userId AND user_service = :userService""", nativeQuery = true)
    List<MessagePrimaryEntity> findByUserIdAndUserService(@Param("userId") String userId, @Param("userService") String userService);
}
