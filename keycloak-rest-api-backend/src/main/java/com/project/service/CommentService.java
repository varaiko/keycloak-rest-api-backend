package com.project.service;

import com.project.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto addNewComment(long storyId, CommentDto commentDto);

    List<CommentDto> getAllStoryComments(long storyId);

    CommentDto changeComment(long storyId, long commentId, String username, CommentDto commentDto);

    void deleteComment(long commentid);
    void deleteAllStoryComments(long storyId);
}
