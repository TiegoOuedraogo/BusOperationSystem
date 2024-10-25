package com.example.nycmta.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusDto {
    private Long busId;
    private String busNumber;
}

