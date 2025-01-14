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

    @GetMapping("/read/{userId}/{userService}")
    public List<MessageSelectViewEntity> readOneUMessage(@PathVariable String userId, @PathVariable UserService userService) {
        return readMessageUseCase.readByUserIdAndUserService(userId, userService);
    }

    @DeleteMapping("/delete/{userId}/{userService}")
    public void deleteOneMessage(@PathVariable String userId, @PathVariable UserService userService) {
        deleteMessageUseCase.deleteByUserIdAndUserService(userId, userService);
    }
}
