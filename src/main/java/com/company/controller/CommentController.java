package com.company.controller;

import com.company.dto.comment.CommentCreateDTO;
import com.company.dto.comment.CommentFullInfoDTO;
import com.company.dto.comment.CommentShortInfoDTO;
import com.company.dto.comment.CommentUpdateDTO;
import com.company.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<?>create(@RequestBody @Valid CommentCreateDTO dto){
        String response = commentService.create(dto);
        return ResponseEntity.ok().body(response);
        
    }
    @PutMapping("/update")
    public ResponseEntity<?>create(@RequestBody @Valid CommentUpdateDTO dto){
        String response = commentService.update(dto);
        return ResponseEntity.ok().body(response);

    }

    //6
    @GetMapping("/adm/{profileId}")
    public ResponseEntity<?> commentListByPorfileId(@PathVariable("profileId") Integer profileId){
        List<CommentShortInfoDTO> dtoList = commentService.commentListByProfileId(profileId);
        return ResponseEntity.ok().body(dtoList);

    }
    //7
    @GetMapping("/user/commentList/{videoId}")
    public ResponseEntity<?> commentList(@PathVariable("videoId") String videoId ){
        List<CommentFullInfoDTO> dtoList = commentService.commentList(videoId);
        return ResponseEntity.ok().body(dtoList);

    }


}
