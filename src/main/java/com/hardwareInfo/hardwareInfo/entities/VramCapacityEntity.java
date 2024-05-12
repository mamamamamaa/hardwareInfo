package com.hardwareInfo.hardwareInfo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vram_capacities")
public class VramCapacityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vram_capacity_id")
    private Long id;

    @Column(name = "vram_capacity")
    private Integer vramCapacity;

    @OneToMany(mappedBy = "vramCapacity")
    private List<GraphicsCardEntity> graphicsCards;
}
