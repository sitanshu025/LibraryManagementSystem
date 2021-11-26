package com.bca.librarymanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "password", length = 20, nullable = false)
    private String password;

    @Column(name = "user_type", nullable = false)
    private UserType type;

    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "description", length = 250, nullable = false)
    private String address;

    @Column(name = "book_count", nullable = false)
    private int bookCount;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REMOVE}, mappedBy = "users")
    private Set<Book> books = new HashSet<>();
}
