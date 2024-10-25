package com.example.nycmta.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusResponseDto {
    private Long busId;
    private String busNumber;

}

