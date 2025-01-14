package com.example.starrocks;

import java.io.Serializable;

public class MessagePrimaryUPK implements Serializable {

    private String userId;
    private UserService userService;
    private Long itemId;
    private ItemService itemService;
}
