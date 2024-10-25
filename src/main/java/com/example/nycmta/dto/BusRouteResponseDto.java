package com.example.nycmta.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusRouteResponseDto {
    private Long routeId;
    private String routeName;
    @NotEmpty(message = "At least one stop is required")
    private List<RouteStopRequestDto> stops;

}

