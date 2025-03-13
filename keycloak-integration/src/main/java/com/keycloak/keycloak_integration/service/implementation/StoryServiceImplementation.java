package com.keycloak.keycloak_integration.service.implementation;

import com.keycloak.keycloak_integration.dto.StoryDto;
import com.keycloak.keycloak_integration.entity.Story;
import com.keycloak.keycloak_integration.exception.ResourceNotFoundException;
import com.keycloak.keycloak_integration.repository.StoryRepository;
import com.keycloak.keycloak_integration.service.StoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoryServiceImplementation implements StoryService {

    private StoryRepository storyRepository;

    private ModelMapper mapper;

    public StoryServiceImplementation(StoryRepository storyRepository, ModelMapper mapper) {
        this.storyRepository = storyRepository;
        this.mapper = mapper;
    }

    public StoryDto createStory(StoryDto storyDto) {
        Story story = mapToEntity(storyDto);

        story.setDate(new Date());
        Story newStory = storyRepository.save(story);

        StoryDto storyResponse = mapToDto(newStory);

        return storyResponse;
    }

    public List<StoryDto> getAllStories() {

        List<Story> allStories = storyRepository.findAll();

        return allStories.stream().map((story) -> mapToDto(story)).collect(Collectors.toList());
    }

    @Override
    public List<StoryDto> getAllClientStories(String roleName) {
        List<Story> allStories;
        String[] roleNameSplitted = roleName.split("_");
        if (roleNameSplitted[1].equals("global")) {
            allStories = storyRepository.findAll().stream().filter(x -> x.getRoleName().split("_")[0].equals(roleNameSplitted[0])).collect(Collectors.toList());
        } else {
            allStories = storyRepository.findAll().stream().filter(x -> x.getRoleName().equals(roleName)).collect(Collectors.toList());
        }
        return allStories.stream().map((story) -> mapToDto(story)).collect(Collectors.toList());
    }

    @Override
    public StoryDto getStoryById(long id) {
        Story story = storyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Story", "id", id));

        return mapToDto(story);
    }

    @Override
    public StoryDto changeStory(StoryDto storyDto, long id) {
        Story story = storyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Story", "id", id));

        story.setContent(storyDto.getContent());
        story.setTitle(storyDto.getTitle());

        Story updatedStory = storyRepository.save(story);

        return mapToDto(updatedStory);
    }

    @Override
    public void deleteStory(long id) {
        storyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Story", "id", id));
        storyRepository.deleteById(id);
    }

    // Convert Entity to DTO
    private StoryDto mapToDto(Story story) {
        StoryDto storyDto = mapper.map(story, StoryDto.class);
        return storyDto;
    }

    // Convert DTO to entity
    private Story mapToEntity(StoryDto storyDto) {
        Story story = mapper.map(storyDto, Story.class);
        return story;
    }

}
