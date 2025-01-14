package com.example.starrocks;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateMessageUseCase {

    private final MessagePrimaryJdbcRepository messagePrimaryJdbcRepository;
    private final MessageDuplicateJdbcRepository messageDuplicateJdbcRepository;

    public ResponseEntity<?> upsert(CreateMessageRequest request) {
        var msg = new MessagePrimaryEntity();
        msg.setUserId(request.getUserId());
        msg.setUserService(request.getUserService());
        msg.setItemId(request.getItemId());
        msg.setItemService(request.getItemService());
        msg.setMessageId(request.getMessageId());
        msg.setTime(request.getTime());

        log.info("upsert msg: {}", msg);
        messagePrimaryJdbcRepository.upsert(
                msg.getUserId(), msg.getUserService(), msg.getItemId(), msg.getItemService(), msg.getMessageId(), msg.getTime());

        return ResponseEntity.ok(msg);
    }

    public ResponseEntity<?> insert(CreateMessageRequest request) {
        var msg = new MessageDuplicateEntity();
        msg.setUserId(request.getUserId());
        msg.setUserService(request.getUserService());
        msg.setItemId(request.getItemId());
        msg.setItemService(request.getItemService());
        msg.setMessageId(request.getMessageId());
        msg.setTime(request.getTime());

        log.info("insert msg: {}", msg);
        messageDuplicateJdbcRepository.insert(
                msg.getUserId(), msg.getUserService(), msg.getItemId(), msg.getItemService(), msg.getMessageId(), msg.getTime());

        return ResponseEntity.ok(msg);
    }
}
