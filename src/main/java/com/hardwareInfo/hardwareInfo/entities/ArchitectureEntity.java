package com.hardwareInfo.hardwareInfo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "architectures")
public class ArchitectureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "architecture_id")
    private Long id;

    @Column(name = "gpu_architecture")
    private String gpuArchitecture;

    @OneToMany(mappedBy = "architecture")
    private List<GpuModelEntity> gpuModels;
}
