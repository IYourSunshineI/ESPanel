package com.yoursunshine.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RgbModule extends KnobModule{
    @Column(nullable = false)
    private String color;
}
