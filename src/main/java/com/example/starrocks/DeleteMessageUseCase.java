package com.example.starrocks;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteMessageUseCase {

    private final MessageDuplicateJpaRepository messageDuplicateJpaRepository;
    private final MessageMapper messageMapper;

    public void delete() {
        List<MessageListDto> messageList = messageDuplicateJpaRepository.findShouldBeDeletedMessage()
                .stream().map(messageMapper::toMessageListDto).toList();

        if (!messageList.isEmpty()) {
            for (MessageListDto message : messageList) {
                log.info("delete message: {}", message);
                messageDuplicateJpaRepository.deleteByItemIdAndItemServiceAndMessageId(message.getItemId(), message.getItemService(), message.getMessageId());
            }
        }
    }
}
