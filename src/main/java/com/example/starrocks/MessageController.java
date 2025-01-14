package com.example.starrocks;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final CreateMessageUseCase createMessageUseCase;
    private final ReadMessageUseCase readMessageUseCase;
    private final DeleteMessageUseCase deleteMessageUseCase;

    @PostMapping("/upsert_primary")
    public ResponseEntity<?> upsertMessagePrimary(@RequestBody CreateMessageRequest request) {
        return createMessageUseCase.upsert(request);
    }

    @PostMapping("/insert_duplicate")
    public ResponseEntity<?> insertMessageDuplicate(@RequestBody CreateMessageRequest request) {
        return createMessageUseCase.insert(request);
    }

    @GetMapping("/read_primary")
    public List<MessagePrimaryEntity> readAllMessagePrimary() {
        return readMessageUseCase.readAllPrimary();
    }

    @GetMapping("/read_duplicate")
    public List<MessageDuplicateEntity> readAllMessageDuplicate() {
        return readMessageUseCase.readAllDuplicate();
    }

    @GetMapping("/read_primary/{userId}/{userService}")
    public List<MessagePrimaryEntity> readOneUserMessagePrimary(@PathVariable String userId, @PathVariable UserService userService) {
        return readMessageUseCase.readByUserIdAndUserServicePrimary(userId, userService);
    }

    @GetMapping("/read_duplicate/{userId}/{userService}")
    public List<MessageSelectViewEntity> readOneUserMessageDuplicate(@PathVariable String userId, @PathVariable UserService userService) {
        return readMessageUseCase.readByUserIdAndUserServiceDuplicate(userId, userService);
    }

    @DeleteMapping("/delete")
    public void deleteMessage() {
        deleteMessageUseCase.delete();
    }
}
