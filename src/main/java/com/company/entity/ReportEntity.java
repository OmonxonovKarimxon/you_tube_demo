package com.company.entity;

import com.company.enums.ReportType;
import com.company.enums.SubscriptionStatus;
import com.company.enums.TagStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "report")
@NoArgsConstructor
public class ReportEntity {
// id,profile_id,content,entity_id(channel)id,profile_id),type(channel,video)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "profile_id")
    private Integer profileId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false,updatable = false,insertable = false)
    private ProfileEntity profile;

    @Column(name = "entity_id",nullable = false, unique = true)
    private String entityId;

    @Column( nullable = false )
    private String content;

    @Column(name = "report_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private ReportType reportType;



    @Column(nullable = false, name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(nullable = false)
    Boolean visible = Boolean.TRUE;


}
