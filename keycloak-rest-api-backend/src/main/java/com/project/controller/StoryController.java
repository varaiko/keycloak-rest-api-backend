package com.project.controller;

import com.project.dto.StoryDto;
import com.project.service.CommentService;
import com.project.service.StoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")

public class StoryController {

    private StoryService storyService;

    private CommentService commentService;

    public StoryController(StoryService storyService, CommentService commentService) {
        this.storyService = storyService;
        this.commentService = commentService;
    }

    @PostMapping("/poststory/{roleName}")
    @PreAuthorize("hasAnyRole(#roleName, 'client_admin')")
    public ResponseEntity<StoryDto> postStory(@RequestBody StoryDto storyDto, @PathVariable(name = "roleName") String roleName, @PathVariable(name = "roleName") String userrole) {
        return new ResponseEntity<>(storyService.createStory(storyDto), HttpStatus.CREATED);
    }

    @GetMapping("/stories")
    @PreAuthorize("hasAnyRole('client_admin')")
    public ResponseEntity<List<StoryDto>> getAllStories() {
        List<StoryDto> stories = storyService.getAllStories();
        return ResponseEntity.ok(stories);
    }

    // Admin - access to all
    @GetMapping("/stories-edit")
    @PreAuthorize("hasAnyRole('client_admin')")
    public ResponseEntity<List<StoryDto>> getAllStoriesToEdit() {
        List<StoryDto> stories = storyService.getAllStories();
        return ResponseEntity.ok(stories);
    }

    // Client global - all this roleName splitted first part
    @GetMapping("/client-stories/{roleName}")
    @PreAuthorize("hasRole(#roleName)")
    public ResponseEntity<List<StoryDto>> getAllClientBasisStories(@PathVariable(name = "roleName") String roleName) {
        List<StoryDto> stories = storyService.getAllClientStories(roleName);
        return ResponseEntity.ok(stories);
    }


    @GetMapping("/stories/{roleName}/{id}")
    @PreAuthorize("hasAnyRole(#roleName, 'client_admin')")
    public ResponseEntity<StoryDto> getStoryById(@PathVariable(name = "roleName") String roleName, @PathVariable(name = "id") long id) {
        StoryDto story = storyService.getStoryById(id);
        return ResponseEntity.ok(story);
    }

    @PutMapping("/stories/changestory/{roleName}/{id}")
    @PreAuthorize("hasAnyRole(#roleName, 'client_admin')")
    public ResponseEntity<StoryDto> changeStory(@PathVariable(name = "roleName") String roleName, @PathVariable(name = "id") long id, @RequestBody StoryDto storyDto) {
        StoryDto storyResponse = storyService.changeStory(storyDto, id);
        return new ResponseEntity<>(storyResponse, HttpStatus.OK);
    }

    @DeleteMapping("/stories/{roleName}/{id}")
    @PreAuthorize("hasAnyRole(#roleName, 'client_admin')")
    public ResponseEntity<String> deleteStory(@PathVariable(name = "roleName") String roleName, @PathVariable(name = "id") long id) {
        commentService.deleteAllStoryComments(id);
        storyService.deleteStory(id);
        return new ResponseEntity<>("Story with comments has been deleted", HttpStatus.OK);
    }
}
