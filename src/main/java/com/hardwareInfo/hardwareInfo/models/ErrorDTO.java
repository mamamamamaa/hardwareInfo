package com.hardwareInfo.hardwareInfo.models;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {
    private String message;
    private Integer status;
}
