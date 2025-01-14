package com.example.starrocks;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteMessageUseCase {

    private final MessageDuplicateJpaRepository messageDuplicateJpaRepository;

    public void deleteByUserIdAndUserService(String userId, UserService userService) {
        var messageList = messageDuplicateJpaRepository.findByUserIdAndUserService(userId, userService.name());



        log.info("messageList: {}", messageList);

        messageDuplicateJpaRepository.deleteByUserIdAndUserService(userId, userService.name(), messageList);
    }
}
