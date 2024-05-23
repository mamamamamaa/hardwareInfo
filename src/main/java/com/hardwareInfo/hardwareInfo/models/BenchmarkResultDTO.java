package com.hardwareInfo.hardwareInfo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BenchmarkResultDTO {
    private Long id;
    private String benchmark;
    private Double score;
    private Long graphicsCardId;
}
