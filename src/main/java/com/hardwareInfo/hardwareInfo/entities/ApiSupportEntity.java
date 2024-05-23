package com.hardwareInfo.hardwareInfo.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "api_supports")
public class ApiSupportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "api_support_id")
    private Long id;

    private String cuda;

    @Column(name = "open_gl")
    private String openGl;

    @Column(name = "direct_x")
    private String directX;

    private String vulcan;

    @ManyToOne
    @JoinColumn(name = "graphics_card_id")
    private GraphicsCardEntity graphicsCard;
}
