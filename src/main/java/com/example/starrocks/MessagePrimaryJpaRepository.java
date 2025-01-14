package com.example.starrocks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagePrimaryJpaRepository extends JpaRepository<MessagePrimaryEntity, Long> {
}
