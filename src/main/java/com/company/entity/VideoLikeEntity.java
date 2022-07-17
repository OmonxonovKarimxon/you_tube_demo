package com.company.entity;

import com.company.enums.LikeStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "video_like")
public class VideoLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "profile_id")
    private Integer profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column(name = "video_id")
    private String videoId;
    @JoinColumn(name = "video_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private VideoEntity video;

    @Column()
    @Enumerated(EnumType.STRING)
    private LikeStatus status;


    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
}
