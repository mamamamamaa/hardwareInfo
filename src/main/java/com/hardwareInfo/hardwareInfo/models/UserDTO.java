package com.hardwareInfo.hardwareInfo.models;

import com.hardwareInfo.hardwareInfo.entities.enums.Role;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String avatarUrl;
    private String email;
    private Role role;
}
