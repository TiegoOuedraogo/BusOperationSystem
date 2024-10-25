package com.example.nycmta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BusRouteRequestDto {
    @NotBlank(message = "Bus Route is required")
    private String routeName;
    @NotEmpty(message = "At least one stop is required")
    private List<RouteStopRequestDto> stops;

}


