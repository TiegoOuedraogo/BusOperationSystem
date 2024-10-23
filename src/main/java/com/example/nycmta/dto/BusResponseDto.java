package com.example.nycmta.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusResponseDto {
    private Long busId;
    private String busNumber;
    private Integer capacity;
}
