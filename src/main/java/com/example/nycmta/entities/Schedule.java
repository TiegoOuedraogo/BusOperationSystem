package com.example.nycmta.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "schedules", schema = "nycmta")

public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long scheduleId;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private BusRoute busRoute;

    @ManyToOne
    @JoinColumn(name = "stop_id")
    private BusStop busStop;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
}

