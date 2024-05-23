package com.hardwareInfo.hardwareInfo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VramTypeDTO {
    private Long id;
    private String vramType;
}
