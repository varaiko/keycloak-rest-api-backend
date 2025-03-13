package com.project.service.implementation;

import com.project.dto.CommentDto;
import com.project.entity.Comment;
import com.project.entity.Story;
import com.project.exception.APIException;
import com.project.exception.ResourceNotFoundException;
import com.project.repository.CommentRepository;
import com.project.repository.StoryRepository;
import com.project.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImplementation implements CommentService {

    private CommentRepository commentRepository;
    private StoryRepository storyRepository;
    private ModelMapper mapper;

    public CommentServiceImplementation(CommentRepository commentRepository, ModelMapper mapper, StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
        this.commentRepository = commentRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDto addNewComment(long storyId, CommentDto commentDto) {

        Comment comment = mapToEntity(commentDto);

        Story story = storyRepository.findById(storyId).orElseThrow(() -> new ResourceNotFoundException("Story", "id", storyId));

        comment.setStory(story);
        comment.setDate(new Date());

        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getAllStoryComments(long storyId) {

        storyRepository.findById(storyId).orElseThrow(() -> new ResourceNotFoundException("Story", "id", storyId));

        List<Comment> comments = commentRepository.findByStoryId(storyId);

        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto changeComment(long storyId, long commentId, String username, CommentDto commentDto) {

        Story story = storyRepository.findById(storyId).orElseThrow(() -> new ResourceNotFoundException("Story", "id", storyId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if (comment.getStory().getId() != story.getId() || !comment.getUsername().equals(username)) {
            try {
                throw new APIException(HttpStatus.BAD_REQUEST, "Comment does not belong to story or is not created by the same user");
            } catch (APIException e) {
                throw new RuntimeException(e);
            }
        }

        comment.setComment(commentDto.getComment());

        Comment updatedComment = commentRepository.save(comment);

        return mapToDto(updatedComment);

    }

    @Override
    public void deleteComment(long commentId) {
        commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        commentRepository.deleteById(commentId);
    }

    @Override
    public void deleteAllStoryComments(long storyId) {
        storyRepository.findById(storyId).orElseThrow(() -> new ResourceNotFoundException("Story", "id", storyId));
        List<Comment> comments = commentRepository.findAll().stream().filter(x -> x.getStory().equals(storyId)).collect(Collectors.toList());
        commentRepository.deleteAllInBatch(comments);
    }

    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = mapper.map(comment, CommentDto.class);
        return commentDto;
    }

    // Convert DTO to entity
    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = mapper.map(commentDto, Comment.class);
        return comment;
    }
}
