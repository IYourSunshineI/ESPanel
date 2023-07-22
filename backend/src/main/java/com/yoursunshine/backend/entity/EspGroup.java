package com.yoursunshine.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EspGroup {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Column(nullable = false, unique = true)
    private String ip_address;

    @Column(columnDefinition = "boolean default false")
    private boolean state;

    @ManyToOne()
    @JoinColumn(nullable = false)
    private Room room;
}
