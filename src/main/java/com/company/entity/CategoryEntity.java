package com.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,name = "admin_id")
    private Integer adminId;

    @Column(nullable = false)
    private Boolean visible = Boolean.TRUE;


    @Column(nullable = false, name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();


}
