package com.company.entity;

import com.company.enums.ChannelStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "channel")
public class ChannelEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(name = "photo_id")
    private String photoId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id",nullable = false,updatable = false,insertable = false)
    private AttachEntity attach;

    @Column(name = "banner_id")
    private String bannerId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banner_id",nullable = false,updatable = false,insertable = false)
    private AttachEntity banner;

    @Column(name = "profile_id")
    private Integer profileId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false,updatable = false,insertable = false)
    private ProfileEntity profile;


    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ChannelStatus status = ChannelStatus.ACTIVE;

    @Column(nullable = false)
    private Boolean visible = Boolean.TRUE;

    @Column(nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

}
