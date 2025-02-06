package com.assignment.management.model.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "student", name = "student_tbl")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "usid", nullable = false)
    private String usid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "class", nullable = false)
    private int studentClass;

    @Column(name = "contact", nullable = false, length = 10)
    private String contact;

    @Column(name = "age", nullable = false)
    private int age;
}
