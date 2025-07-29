package com.example.demo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "table_1")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
}