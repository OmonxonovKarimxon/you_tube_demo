package com.company.entity;

import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "profile")
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(  unique = true)
    private String tempEmail;
    @Column
    private String name;

    @Column
    private String surname;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfileStatus status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfileRole role;

    @Column(nullable = false)
    private Boolean visible = Boolean.TRUE;

    @Column(nullable = false)
    private String password;

    @Column(name = "main_photo")
    private String photoId;
    @JoinColumn(name = "main_photo", nullable = false,insertable = false,updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private  AttachEntity mainPhoto;
}
