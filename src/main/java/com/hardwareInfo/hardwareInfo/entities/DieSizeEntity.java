package com.hardwareInfo.hardwareInfo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "die_sizes")
public class DieSizeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "die_size_id")
    private Long id;

    @Column(name = "die_size")
    private Integer dieSize;

    @OneToMany(mappedBy = "dieSize")
    private List<GpuModelEntity> gpuModels;
}
