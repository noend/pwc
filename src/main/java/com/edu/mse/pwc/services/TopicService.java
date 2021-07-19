package com.edu.mse.pwc.services;

import com.edu.mse.pwc.dtos.TopicDto;
import com.edu.mse.pwc.exceptions.TopicNotFoundException;
import com.edu.mse.pwc.mappers.TopicMapper;
import com.edu.mse.pwc.persistence.entities.TopicEntity;
import com.edu.mse.pwc.persistence.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    public TopicDto createTopic(TopicDto topic) {
        TopicEntity topicEntity = topicMapper.topicDtoToEntity(topic);
        TopicEntity savedTopic = topicRepository.save(topicEntity);
        return topicMapper.topicEntityToDto(savedTopic);
    }

    public TopicDto updateTopic(TopicDto topic) {
        Optional<TopicEntity> topicResult = topicRepository.findById(topic.getId());
            if (topicResult.isPresent()) {
                TopicEntity topicEntity = topicResult.get();
                topicEntity.setTitle(topic.getTitle());
                TopicEntity updated = topicRepository.save(topicEntity);
                return topicMapper.topicEntityToDto(updated);
            }
        throw new TopicNotFoundException("No topic with title " + topic.getTitle() + " was found");
    }

    public TopicDto getTopic(Long id) {
        Optional<TopicEntity> byId = topicRepository.findById(id);
        if (byId.isPresent()) {
            TopicEntity topicEntity = byId.get();
            TopicDto topicDto = topicMapper.topicEntityToDto(topicEntity);

            extracted(topicEntity, topicDto, topicRepository);

            return topicMapper.topicEntityToDto(topicEntity);
        }
        throw new TopicNotFoundException("No topic with id " + id + " was found");
    }

    private static void extracted(TopicEntity topicEntity, TopicDto topicDto, TopicRepository topicRepository) {
        if(topicDto.getViews() == null) {
            topicEntity.setViews((long)1);
        } else {
            long views = topicDto.getViews();
            topicEntity.setViews(views + (long)1);
        }
        TopicEntity updated = topicRepository.save(topicEntity);
    }


    public List<TopicDto> getAllTopics() {
        return topicRepository.findAll().stream().map(topicMapper::topicEntityToDto).collect(Collectors.toList());
    }

}
