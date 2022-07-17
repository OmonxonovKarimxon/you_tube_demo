package com.company.entity;

import com.company.enums.LikeStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comment_like")
public class CommentLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private ProfileEntity profile;

    @Column(name = "commet_id")
    private String videoId;
    @JoinColumn(name = "comment_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private CommentEntity comment;

    @Column()
    @Enumerated(EnumType.STRING)
    private LikeStatus status;


    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();
}
