package com.example.nycmta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusRequestDto {

    @NotBlank(message = "Bus number is required")
    private String busNumber;

    @NotNull(message = "Capacity is required")
    private Integer capacity;
}
