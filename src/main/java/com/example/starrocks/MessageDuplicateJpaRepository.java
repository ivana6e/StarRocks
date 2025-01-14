package com.example.starrocks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDuplicateJpaRepository extends JpaRepository<MessageDuplicateEntity, Long> {

    @Query(value = """
    SELECT
        user_id AS userId,
        user_service AS userService,
        item_id AS itemId,
        item_service AS itemService,
        max(message_id) AS messageId
    FROM message_duplicate
    WHERE user_id = :userId AND user_service = :userService
    GROUP BY user_id, user_service, item_id, item_service""", nativeQuery = true)
    List<MessageSelectViewEntity> findByUserIdAndUserService(@Param("userId") String userId, @Param("userService") String userService);

    @Query(value = """
    DELETE FROM message_duplicate
    WHERE user_id = :userId AND user_service = :userService
    AND (user_id, user_service, item_id, item_service, message_id) NOT IN :messageList
    )""", nativeQuery = true)
    void deleteByUserIdAndUserService(@Param("userId") String userId, @Param("userService") String userService, @Param("messageList") List<MessageSelectViewEntity> messageList);
}
