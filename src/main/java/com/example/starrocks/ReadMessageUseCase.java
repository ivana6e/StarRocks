package com.example.starrocks;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReadMessageUseCase {

    private final MessagePrimaryJpaRepository messagePrimaryJpaRepository;
    private final MessageDuplicateJpaRepository messageDuplicateJpaRepository;

    public List<MessagePrimaryEntity> readAllPrimary() {
        return messagePrimaryJpaRepository.findAll();
    }

    public List<MessageDuplicateEntity> readAllDuplicate() {
        return messageDuplicateJpaRepository.findAll();
    }

    public List<MessagePrimaryEntity> readByUserIdAndUserServicePrimary(String userId, UserService userService) {
        return messagePrimaryJpaRepository.findByUserIdAndUserService(userId, userService.name());
    }

    public List<MessageSelectViewEntity> readByUserIdAndUserServiceDuplicate(String userId, UserService userService) {
        return messageDuplicateJpaRepository.findByUserIdAndUserService(userId, userService.name());
    }
}
