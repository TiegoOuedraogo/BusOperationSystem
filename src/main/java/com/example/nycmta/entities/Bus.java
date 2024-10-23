package com.example.nycmta.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "bus", schema = "nycmta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_id")
    private Long busId;

    @Column(name = "bus_number", nullable = false, unique = true)
    private String busNumber;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> schedules;
}
