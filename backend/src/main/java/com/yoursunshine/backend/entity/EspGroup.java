package com.yoursunshine.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

    private String ipAddress;

    @Column(columnDefinition = "boolean default false")
    private boolean state;
}
