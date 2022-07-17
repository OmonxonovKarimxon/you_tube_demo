package com.company.controller;


import com.company.dto.comment.CommentLikeCreateDTO;
import com.company.dto.comment.CommentLikeDTO;
import com.company.dto.video.VideoLikeCreateDTO;
import com.company.dto.video.VideoLikeDTO;
import com.company.service.CommentLikeService;
import com.company.service.VideoLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/comment_like")
@RestController
public class CommetLikeController {

    private final CommentLikeService commentLikeService;

    public CommetLikeController(CommentLikeService commentLikeService) {
        this.commentLikeService = commentLikeService;
    }

    @PostMapping("/like")
    public ResponseEntity<Void> like(@RequestBody CommentLikeCreateDTO dto) {

        commentLikeService.commentLike(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/dislike")
    public ResponseEntity<Void> dislike(@RequestBody CommentLikeCreateDTO dto) {

        commentLikeService.commentDislike(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove")
    public ResponseEntity<Void> remove(@RequestBody CommentLikeCreateDTO dto) {
        commentLikeService.removeLike(dto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/commentListByLiked")
    public ResponseEntity<?> commentListByLiked() {
       List<CommentLikeDTO> list =  commentLikeService.commentListByLiked();
        return ResponseEntity.ok().body(list);
    }

}