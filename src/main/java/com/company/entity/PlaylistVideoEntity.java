package com.company.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "video_playlist")
@NoArgsConstructor
public class PlaylistVideoEntity {
// id,playlist_id,video_id,created_date, order_num
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "video_id")
    private String videoId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", nullable = false, insertable = false, updatable = false)
    private VideoEntity video;

    @Column(name = "playlist_id")
    private Integer playlistId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id", nullable = false, insertable = false, updatable = false)
    private PlaylistEntity playlist;


    @Column(nullable = false, name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(nullable = false)
    Boolean visible = Boolean.TRUE;

}
