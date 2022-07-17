package com.company.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comment")
@NoArgsConstructor
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "profile_id")
    private Integer profileId;

    @JoinColumn(nullable = false, name = "profile_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ProfileEntity profile;

    @Column(name = "video_id")
    private String videoId;

    @JoinColumn(nullable = false, name = "video_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private VideoEntity video;

    @Column(name = "comment_id")
    private Integer commentId;

    @JoinColumn(name = "comment_id",nullable = false,insertable = false,updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private CommentEntity reply;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean visible = Boolean.TRUE;

    @Column(nullable = false, name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();


}
