package com.hardwareInfo.hardwareInfo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gpu_models")
public class GpuModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gpu_model_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "die_size_id")
    private DieSizeEntity dieSize;

    @ManyToOne
    @JoinColumn(name = "architecture_id")
    private ArchitectureEntity architecture;

    @ManyToOne
    @JoinColumn(name = "gpu_vendor")
    private VendorEntity gpuVendor;

    @Column(name = "gpu_name")
    private String gpuName;

    @Column(name = "gpu_rev")
    private String gpuRev;

    @Column(name = "shader_processors")
    private Integer shaderProcessors;

    @Column(name = "rop")
    private Integer rop;

    @Column(name = "tmu")
    private Integer tmu;

    @OneToMany(mappedBy = "gpuModel")
    private List<GraphicsCardEntity> graphicsCards;
}
