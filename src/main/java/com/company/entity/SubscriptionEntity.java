package com.company.entity;

import com.company.enums.SubscriptionStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "profile_id")
    private Integer profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column(name = "commet_id")
    private Integer commentId;
    @JoinColumn(name = "comment_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private CommentEntity comment;

    @Column(name = "channel_id")
    private String channelId;
    @JoinColumn(name = "channel_id", nullable = false,insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ChannelEntity channel;

    @Column()
    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;


    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;
}
