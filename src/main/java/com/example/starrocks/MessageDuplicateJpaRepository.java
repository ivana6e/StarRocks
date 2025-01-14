package com.example.starrocks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
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
    SELECT DISTINCT
        item_id AS itemId,
        item_service AS itemService,
        message_id AS messageId
    FROM message_duplicate
    WHERE (item_id, item_service, message_id) NOT IN (
        SELECT item_id, item_service, max(message_id)
        FROM message_duplicate
        GROUP BY item_id, item_service
    )""", nativeQuery = true)
    List<MessageSelectViewEntity> findShouldBeDeletedMessage();

    @Modifying
    @Query(value = """
    DELETE FROM message_duplicate
    WHERE item_id = :itemId AND item_service = :itemService AND message_id = :messageId""", nativeQuery = true)
    void deleteByItemIdAndItemServiceAndMessageId(@Param("itemId") Long itemId, @Param("itemService") String itemService, @Param("messageId") Long messageId);
}
