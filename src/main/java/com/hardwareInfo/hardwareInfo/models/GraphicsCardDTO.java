package com.hardwareInfo.hardwareInfo.models;

import com.hardwareInfo.hardwareInfo.entities.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraphicsCardDTO {
    private Long id;
    private String modelName;
    private GpuModelEntity gpuModel;
    private VramTypeEntity vramType;
    private VramCapacityEntity vramCapacity;
    private SubVendorEntity subVendor;
    private Date releaseDate;
    private String itemDescription;
    private TdpEntity tdp;
    private Double vramFrequency;
    private Double gpuFrequency;
}
