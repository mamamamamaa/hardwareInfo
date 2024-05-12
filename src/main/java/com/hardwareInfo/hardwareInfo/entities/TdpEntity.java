package com.hardwareInfo.hardwareInfo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tpd")
public class TdpEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tdp_id")
    private Long id;

    @Column(name = "tdp_value")
    private Integer tdpValue;

    @OneToMany(mappedBy = "tdp")
    private List<GraphicsCardEntity> graphicsCards;
}
