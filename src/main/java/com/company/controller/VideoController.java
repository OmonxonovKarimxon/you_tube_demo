package com.company.controller;

import com.company.dto.ResponseInfoDTO;
import com.company.dto.playlist.PlaylistCreateDTO;
import com.company.dto.playlist.PlaylistDTO;
import com.company.dto.playlist.PlaylistStatusDTO;
import com.company.dto.playlist.PlaylistUpdateDTO;
import com.company.dto.video.*;
import com.company.service.PlaylistService;
import com.company.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("/user/crate")
    public ResponseEntity<?> create(@RequestBody @Valid VideoCreateDTO dto){
        String response = videoService.create(dto);
        return  ResponseEntity.ok().body(response);

    }
    @PutMapping("/user/update")
    public ResponseEntity<?> update(@RequestBody VideoUpdateDTO dto) {

        String update = videoService.update(dto);

        return ResponseEntity.ok(update);
    }
    @PutMapping("/user/changeStatus")
    public ResponseEntity<?> changeStatus(@RequestBody VideoStatusDTO dto) {

        String response = videoService.changeStatus(dto);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/byId/{id}")
    public ResponseEntity<?>IncreaseVideo(@PathVariable("id") String id){
        String response =   videoService.IncreaseVideo(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/user/pagination/{categoryId}")
    public ResponseEntity<?> getShortInfoByCategoryKey(@PathVariable("categoryId") Integer categoryId,
                                                   @RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "size", defaultValue = "5") int size) {

        PageImpl<VideoShortInfoDTO> list = videoService.pagination(page, size, categoryId);
        return ResponseEntity.ok().body(list);
    }


    @GetMapping("/user/{title}")
    public ResponseEntity<?>byId(@PathVariable("title") String title){
        VideoShortInfoDTO dto =   videoService.findByTitle(title);
        return ResponseEntity.ok().body(dto);
    }
}
