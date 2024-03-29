package com.yoursunshine.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "PinNumberAndGroup",
                columnNames = {"pinNumber", "group_id"})
})
public abstract class KnobModule {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private Integer pinNumber;

    @ManyToOne()
    @JoinColumn(nullable = false)
    private EspGroup group;
}
