package com.hardwareInfo.hardwareInfo.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "benchmark_results")
public class BenchmarkResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "benchmark_result_id")
    private Long id;

    private String benchmark;

    private Double score;

    @ManyToOne
    @JoinColumn(name = "graphics_card_id")
    private GraphicsCardEntity graphicsCard;
}
