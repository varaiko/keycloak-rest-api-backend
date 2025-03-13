package com.keycloak.keycloak_integration.controller;

import com.keycloak.keycloak_integration.dto.CommentDto;
import com.keycloak.keycloak_integration.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stories/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/addcomment/{roleName}/{postid}")
    @PreAuthorize("hasAnyRole(#roleName, 'client_admin')")
    public ResponseEntity<CommentDto> createComment(@PathVariable(name = "roleName") String roleName, @PathVariable(name = "postid") long postid, @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.addNewComment(postid, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/{roleName}/{storyid}")
    @PreAuthorize("hasAnyRole(#roleName, 'client_admin')")
    public List<CommentDto> getStoryComments(@PathVariable(name = "roleName") String roleName, @PathVariable(name = "storyid") long storyid) {
        return commentService.getAllStoryComments(storyid);
    }

    @PutMapping("/{roleName}/{storyid}/{commentid}/{username}")
    @PreAuthorize("hasAnyRole(#roleName, 'client_admin')")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(name = "roleName") String roleName, @PathVariable(name = "storyid") long storyid, @PathVariable(name = "commentid") long commentid, @PathVariable(name = "username") String username, @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.changeComment(storyid, commentid, username, commentDto), HttpStatus.OK);
    }

    @DeleteMapping("/{roleName}/{commentId}/{username}")
    @PreAuthorize("hasAnyRole(#roleName, 'client_admin')")
    public ResponseEntity<String> deleteComment(@PathVariable(name = "roleName") String roleName, @PathVariable(name = "commentId") long commentId, @PathVariable(name = "username") String username) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }

}
