package com.edu.mse.pwc.mappers;

import com.edu.mse.pwc.dtos.TopicDto;
import com.edu.mse.pwc.persistence.entities.TopicEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TopicMapper {

    TopicEntity topicDtoToEntity(TopicDto dto);

    TopicDto topicEntityToDto(TopicEntity dto);
}
