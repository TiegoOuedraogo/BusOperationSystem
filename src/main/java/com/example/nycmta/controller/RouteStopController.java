package com.example.nycmta.controller;

import com.example.nycmta.dto.RouteStopRequestDto;
import com.example.nycmta.dto.RouteStopResponseDto;
import com.example.nycmta.service.RouteStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/route-stops")
public class RouteStopController {

    private final RouteStopService routeStopService;

    @Autowired
    public RouteStopController(RouteStopService routeStopService) {
        this.routeStopService = routeStopService;
    }

    @PostMapping
    public ResponseEntity<RouteStopResponseDto> createRouteStop(@RequestBody RouteStopRequestDto routeStopRequestDto) {
        RouteStopResponseDto createdRouteStop = routeStopService.createRouteStop(routeStopRequestDto);
        return new ResponseEntity<>(createdRouteStop, HttpStatus.CREATED);
    }

    @GetMapping("/{routeId}/{stopId}")
    public ResponseEntity<RouteStopResponseDto> getRouteStopById(@PathVariable Long routeId, @PathVariable Long stopId) {
        RouteStopResponseDto routeStopResponse = routeStopService.getRouteStopById(routeId, stopId);
        return new ResponseEntity<>(routeStopResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RouteStopResponseDto>> getAllRouteStops() {
        List<RouteStopResponseDto> routeStops = routeStopService.getAllRouteStops();
        return new ResponseEntity<>(routeStops, HttpStatus.OK);
    }

    @PutMapping("/{routeId}/{stopId}")
    public ResponseEntity<RouteStopResponseDto> updateRouteStop(@PathVariable Long routeId, @PathVariable Long stopId,
                                                                @RequestBody RouteStopRequestDto routeStopRequestDto) {
        RouteStopResponseDto updatedRouteStop = routeStopService.updateRouteStop(routeId, stopId, routeStopRequestDto);
        return new ResponseEntity<>(updatedRouteStop, HttpStatus.OK);
    }

    @GetMapping("/{routeId}/order/{stopOrder}")
    public ResponseEntity<RouteStopResponseDto> getRouteStopByOrder(@PathVariable Long routeId, @PathVariable Integer stopOrder) {
        RouteStopResponseDto routeStopResponse = routeStopService.getRouteStopByOrder(routeId, stopOrder);
        return new ResponseEntity<>(routeStopResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{routeId}/{stopId}")
    public ResponseEntity<Void> deleteRouteStop(@PathVariable Long routeId, @PathVariable Long stopId) {
        routeStopService.deleteRouteStop(routeId, stopId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

