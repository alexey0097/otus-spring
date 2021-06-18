package ru.diasoft.spring.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "USER_PASSWORD")
    private String password;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "ROLES")
    private String roles;
}
