package com.hardwareInfo.hardwareInfo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vram_types")
public class VramTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vram_type_id")
    private Long id;

    @Column(name = "vram_type")
    private String vramType;

    @OneToMany(mappedBy = "vramType")
    private List<GraphicsCardEntity> graphicsCards;
}
