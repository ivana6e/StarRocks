package com.example.starrocks;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "message_duplicate")
@IdClass(MessageDuplicateUPK.class)
public class MessageDuplicateEntity {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Id
    @Column(name = "user_service")
    @Enumerated(EnumType.STRING)
    private UserService userService;

    @Id
    @Column(name = "item_id")
    private Long itemId;

    @Id
    @Column(name = "item_service")
    @Enumerated(EnumType.STRING)
    private ItemService itemService;

    @Id
    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "time")
    private OffsetDateTime time;
}
