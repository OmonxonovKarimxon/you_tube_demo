package com.company.entity;

import com.company.enums.ChannelStatus;
import com.company.enums.VideoStatus;
import com.company.enums.VideoType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "video")
public class VideoEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "preview_attach_id")
    private String previewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preview_attach_id", nullable = false, insertable = false, updatable = false)
    private AttachEntity preview;

    @Column(nullable = false)
    private String title;

    @Column(name = "attach_id")
    private String attachId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id", nullable = false, insertable = false, updatable = false)
    private AttachEntity attach;

    // id(uuid), (key), preview_attach_id,title,category_id,attach_id,created_date,published_date,status(private,public),
//    type(video,short),view_count,shared_count,description,channel_id,(like_count,dislike_count)

    @Column(name = "category_id")
    private Integer categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false, insertable = false, updatable = false)
    private CategoryEntity category;



    @Column(name = "types", nullable = false)
    @Enumerated(EnumType.STRING)
    private VideoType types = VideoType.VIDEO ;

    @Column(name = "view_count")
    private Integer viewCount = 0;

    @Column(name = "shared_count")
    private Integer sharedCount = 0;

    @Column
    private String description;

    @Column(name = "channel_id")
    private String channelId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", nullable = false, insertable = false, updatable = false)
    private ChannelEntity channel;

    @Column(name = "like_id")
    private Integer likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "like_id", nullable = false, insertable = false, updatable = false)
    private VideoLikeEntity like;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private VideoStatus status = VideoStatus.PUBLIC;

    @Column(nullable = false)
    private Boolean visible = Boolean.TRUE;

    @Column(nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

}
