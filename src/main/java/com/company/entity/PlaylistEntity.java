package com.company.entity;

import com.company.enums.PlaylistStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "play_list")
public class PlaylistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name = "channel_id")
    private String channelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(  name = "channel_id",nullable = false,insertable = false,updatable = false)
    private ChannelEntity channel;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(  name = "status")
    private PlaylistStatus status = PlaylistStatus.PUBLIC;

    @Column(name = "orders")
    private Integer orders;

    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();


}
