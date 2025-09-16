package com.scm.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "social_links")
public class SocialLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Contact contact;
}
