package com.example.starrocks;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MessageMapper {

    @Mapping(source = "itemId", target = "itemId")
    @Mapping(source = "itemService", target = "itemService")
    @Mapping(source = "messageId", target = "messageId")
    MessageListDto toMessageListDto(MessageSelectViewEntity entity);
}
