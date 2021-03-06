package com.pinguin.service.serviceInterface;

import com.pinguin.entity.Bug;
import com.pinguin.entity.Story;
import com.pinguin.model.api.Assignment;

import java.util.List;
import java.util.UUID;

public interface StoriesService {
    List<Story> getStories(String title);
    Story getStoryById(UUID issueId);
    Story createStory(Story story);
    Story updateStory(Story story);
    void deleteStory(UUID issueId);
    void assign(List<Assignment> assignments);
    void deleteAll();

}
