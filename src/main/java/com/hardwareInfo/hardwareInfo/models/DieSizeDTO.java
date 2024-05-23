package com.hardwareInfo.hardwareInfo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DieSizeDTO {
    private Long id;
    private Integer dieSize;
}
