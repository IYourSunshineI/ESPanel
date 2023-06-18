package com.yoursunshine.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Room {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
}
