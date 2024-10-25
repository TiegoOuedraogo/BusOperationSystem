package com.example.nycmta.controller;

import com.example.nycmta.dto.BusRouteRequestDto;
import com.example.nycmta.dto.BusRouteResponseDto;
import com.example.nycmta.service.BusRouteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bus-routes")
public class BusRouteController {

    private final BusRouteService busRouteService;

    @Autowired
    public BusRouteController(BusRouteService busRouteService) {
        this.busRouteService = busRouteService;
    }

    @PostMapping
    public ResponseEntity<BusRouteResponseDto> createBusRoute(@Valid @RequestBody BusRouteRequestDto busRouteRequestDto) {
        BusRouteResponseDto createdRoute = busRouteService.createBusRoute(busRouteRequestDto);
        return new ResponseEntity<>(createdRoute, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusRouteResponseDto> getBusRouteById(@PathVariable Long id) {
        BusRouteResponseDto route = busRouteService.getBusRouteById(id);
        return ResponseEntity.ok(route);
    }

    @GetMapping
    public ResponseEntity<List<BusRouteResponseDto>> getAllRoutes() {
        List<BusRouteResponseDto> routes = busRouteService.getAllRoutes();
        return ResponseEntity.ok(routes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusRouteResponseDto> updateBusRoute(@PathVariable Long id, @Valid @RequestBody BusRouteRequestDto busRouteRequestDto) {
        BusRouteResponseDto updatedRoute = busRouteService.updateBusRoute(id, busRouteRequestDto);
        return ResponseEntity.ok(updatedRoute);
    }


}

