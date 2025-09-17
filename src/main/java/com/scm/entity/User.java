package com.scm.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

    private String name;

    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    private String about;

    private String profilePicture;

    private boolean isActive=false;

    // Role
    private Role role=Role.ANONYMOUS;

    // User Authentication Method
    private Provider provider=Provider.SELF;

    // Contacts
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();
}
