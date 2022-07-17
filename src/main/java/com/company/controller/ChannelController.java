package com.company.controller;

import com.company.dto.channel.ChannelCreateDTO;
import com.company.dto.channel.ChannelDTO;
import com.company.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    //user
    @PostMapping("/user/create")
    public ResponseEntity<?> create(@RequestBody @Valid ChannelCreateDTO dto) {
        String response = channelService.create(dto);
        return ResponseEntity.ok().body(response);
    }

    // admin or user
    @PutMapping("/adminAndUser/update")
    public ResponseEntity<?> update(@RequestBody ChannelDTO dto) {
        String response = channelService.update(dto);
        return ResponseEntity.ok().body(response);
    }

    //admin
    @GetMapping("/pagination")
    public ResponseEntity<?> pagination(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "5") int size) {

        PageImpl<ChannelDTO> list = channelService.pagination(page, size);
        return ResponseEntity.ok().body(list);
    }

    //get by id
    @GetMapping("/adminAndUser/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        ChannelDTO byId = channelService.getById(id);
        return ResponseEntity.ok().body(byId);
    }


    //user's channel list
    @GetMapping("/user/channelList")
    public ResponseEntity<List<ChannelDTO>> list() {
        List<ChannelDTO> list = channelService.list();
        return ResponseEntity.ok().body(list);
    }

}
