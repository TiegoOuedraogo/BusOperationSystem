package com.example.nycmta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusRequestDto {
    @NotBlank(message = "Bus number is required")
    private String busNumber;
}


