package com.hardwareInfo.hardwareInfo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "graphics_cards")
public class GraphicsCardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "graphics_card_id")
    private Long id;

    @Column(name = "model_name")
    private String modelName;

    @ManyToOne
    @JoinColumn(name = "gpu_model_id")
    private GpuModelEntity gpuModel;

    @ManyToOne
    @JoinColumn(name = "vram_type_id")
    private VramTypeEntity vramType;

    @ManyToOne
    @JoinColumn(name = "vram_capacity_id")
    private VramCapacityEntity vramCapacity;

    @ManyToOne
    @JoinColumn(name = "gpu_sub_vendor_id")
    private SubVendorEntity subVendor;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "item_description")
    private String itemDescription;

    @ManyToOne
    @JoinColumn(name = "tdp_id")
    private TdpEntity tdp;

    @Column(name = "vram_frequency")
    private Double vramFrequency;

    @Column(name = "gpu_frequency")
    private Double gpuFrequency;

    @OneToMany(mappedBy = "graphicsCard")
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "graphicsCard")
    private List<ApiSupportEntity> apiSupports;

    @OneToMany(mappedBy = "graphicsCard")
    private List<BenchmarkResultEntity> benchmarkResults;
}
