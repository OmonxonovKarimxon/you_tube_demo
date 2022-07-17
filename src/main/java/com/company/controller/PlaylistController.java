package com.company.controller;

import com.company.dto.ResponseInfoDTO;
import com.company.dto.channel.ChannelDTO;
import com.company.dto.playlist.PlaylistCreateDTO;
import com.company.dto.playlist.PlaylistDTO;
import com.company.dto.playlist.PlaylistStatusDTO;
import com.company.dto.playlist.PlaylistUpdateDTO;
import com.company.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @PostMapping("/user/create")
    public ResponseEntity<?> create(@RequestBody PlaylistCreateDTO dto){

        String response = playlistService.create(dto);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/user/update")
    public ResponseEntity<?> update(@RequestBody PlaylistUpdateDTO dto) {

        PlaylistDTO update = playlistService.update(dto);

        return ResponseEntity.ok(update);
    }
    @PutMapping("/user/changeStatus")
    public ResponseEntity<?> changeStatus(@RequestBody PlaylistStatusDTO dto) {

        String response = playlistService.changeStatus(dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/userAndAdmin/{playlistId}")
    public ResponseEntity<?> changeVisible(@PathVariable("playlistId") Integer pId) {

        ResponseInfoDTO dto = playlistService.changeVisible(pId);

        return ResponseEntity.ok(dto);
    }

     @GetMapping("adm/pagination")
    public ResponseEntity<?> pagination(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "5") int size) {


         PageImpl<PlaylistDTO> list = playlistService.pagination(page, size);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<?>byId(@PathVariable("id") Integer id){
      PlaylistDTO dto =   playlistService.findById(id);
      return ResponseEntity.ok().body(dto);
    }
}
