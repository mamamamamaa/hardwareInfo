package com.hardwareInfo.hardwareInfo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sub_vendors")
public class SubVendorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_vendor_id")
    private Long id;

    @Column(name = "gpu_vendor")
    private String gpuVendor;

    @OneToMany(mappedBy = "subVendor")
    private List<GraphicsCardEntity> graphicsCards;
}
