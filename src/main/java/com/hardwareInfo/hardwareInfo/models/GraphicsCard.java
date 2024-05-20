package com.hardwareInfo.hardwareInfo.models;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GraphicsCard {
    private Long id;
    private String modelName;
    private String gpuName; // Відображення gpu_name з gpu_model
    private String vramType;
    private Integer vramCapacity;
    private String subVendor;
    private Date releaseDate;
    private String itemDescription;
    private Integer tdp;
    private Double vramFrequency;
    private Double gpuFrequency;
}
