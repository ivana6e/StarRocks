package com.example.starrocks;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class CreateMessageRequest {

    private String userId;
    private UserService userService;
    private Long itemId;
    private ItemService itemService;
    private Long messageId;
    private OffsetDateTime time;
}
