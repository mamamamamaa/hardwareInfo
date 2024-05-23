package com.hardwareInfo.hardwareInfo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiSupportDTO {
    private Long id;
    private String cuda;
    private String openGl;
    private String directX;
    private String vulcan;
    private Long graphicsCardId;
}
