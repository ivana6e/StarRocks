package com.example.starrocks;

import lombok.Data;

@Data
public class MessageListDto {

    String userId;
    String userService;
    Long itemId;
    String itemService;
    Long messageId;
}
