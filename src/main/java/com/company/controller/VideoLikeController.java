package com.company.controller;


import com.company.dto.video.VideoLikeCreateDTO;
import com.company.dto.video.VideoLikeDTO;
import com.company.service.VideoLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/video_like")
@RestController
public class VideoLikeController {

    @Autowired
    private VideoLikeService videoLikeService;

    @PostMapping("/like")
    public ResponseEntity<Void> like(@RequestBody VideoLikeCreateDTO dto) {

        videoLikeService.videoLike(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/dislike")
    public ResponseEntity<Void> dislike(@RequestBody VideoLikeCreateDTO dto) {

        videoLikeService.videoDislike(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove")
    public ResponseEntity<Void> remove(@RequestBody VideoLikeCreateDTO dto) {
        videoLikeService.removeLike(dto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/videoListByLiked")
    public ResponseEntity<?> videoListByLiked() {
       List<VideoLikeDTO> list =  videoLikeService.videoListByLiked();
        return ResponseEntity.ok().body(list);
    }

}