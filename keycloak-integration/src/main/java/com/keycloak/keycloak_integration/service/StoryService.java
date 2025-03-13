package com.keycloak.keycloak_integration.service;

import com.keycloak.keycloak_integration.dto.StoryDto;

import java.util.List;

public interface StoryService {

    StoryDto createStory(StoryDto storyDto);

    List<StoryDto> getAllStories();

    List<StoryDto> getAllClientStories(String roleName);

    StoryDto getStoryById(long id);

    StoryDto changeStory(StoryDto storyDto, long id);

    void deleteStory(long id);
}
