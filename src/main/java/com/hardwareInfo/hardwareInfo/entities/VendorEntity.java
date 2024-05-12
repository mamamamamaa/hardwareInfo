package com.hardwareInfo.hardwareInfo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vendors")
public class VendorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_id")
    private Long id;

    @Column(name = "gpu_vendor")
    private String gpuVendor;

    @OneToMany(mappedBy = "gpuVendor")
    private List<GpuModelEntity> gpuModels;
}
