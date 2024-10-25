package com.example.nycmta.service.serviceImpl;

import com.example.nycmta.dto.ScheduleRequestDto;
import com.example.nycmta.dto.ScheduleResponseDto;
import com.example.nycmta.entities.Schedule;
import com.example.nycmta.entities.Bus;
import com.example.nycmta.entities.BusRoute;
import com.example.nycmta.entities.BusStop;
import com.example.nycmta.repository.ScheduleRepository;
import com.example.nycmta.repository.BusRepository;
import com.example.nycmta.repository.BusRouteRepository;
import com.example.nycmta.repository.BusStopRepository;
import com.example.nycmta.service.ResourceNotFoundException;
import com.example.nycmta.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final BusRepository busRepository;
    private final BusRouteRepository busRouteRepository;
    private final BusStopRepository busStopRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, BusRepository busRepository,
                               BusRouteRepository busRouteRepository, BusStopRepository busStopRepository) {
        this.scheduleRepository = scheduleRepository;
        this.busRepository = busRepository;
        this.busRouteRepository = busRouteRepository;
        this.busStopRepository = busStopRepository;
    }

    @Override
    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto) {
        Bus bus = busRepository.findById(scheduleRequestDto.getBusId())
                .orElseThrow(() -> new ResourceNotFoundException("Bus", "busId", scheduleRequestDto.getBusId()));
        BusRoute busRoute = busRouteRepository.findById(scheduleRequestDto.getRouteId())
                .orElseThrow(() -> new ResourceNotFoundException("BusRoute", "routeId", scheduleRequestDto.getRouteId()));
        BusStop busStop = busStopRepository.findById(scheduleRequestDto.getStopId())
                .orElseThrow(() -> new ResourceNotFoundException("BusStop", "stopId", scheduleRequestDto.getStopId()));

        Schedule schedule = Schedule.builder()
                .bus(bus)
                .busRoute(busRoute)
                .busStop(busStop)
                .departureTime(scheduleRequestDto.getDepartureTime())
                .arrivalTime(scheduleRequestDto.getArrivalTime())
                .build();

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return mapToScheduleResponseDto(savedSchedule);
    }

    @Override
    public ScheduleResponseDto getScheduleById(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule", "scheduleId", scheduleId));
        return mapToScheduleResponseDto(schedule);
    }

    @Override
    public List<ScheduleResponseDto> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(this::mapToScheduleResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long scheduleId, ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule", "scheduleId", scheduleId));

        Bus bus = busRepository.findById(scheduleRequestDto.getBusId())
                .orElseThrow(() -> new ResourceNotFoundException("Bus", "busId", scheduleRequestDto.getBusId()));
        BusRoute busRoute = busRouteRepository.findById(scheduleRequestDto.getRouteId())
                .orElseThrow(() -> new ResourceNotFoundException("BusRoute", "routeId", scheduleRequestDto.getRouteId()));
        BusStop busStop = busStopRepository.findById(scheduleRequestDto.getStopId())
                .orElseThrow(() -> new ResourceNotFoundException("BusStop", "stopId", scheduleRequestDto.getStopId()));

        schedule.setBus(bus);
        schedule.setBusRoute(busRoute);
        schedule.setBusStop(busStop);
        schedule.setDepartureTime(scheduleRequestDto.getDepartureTime());
        schedule.setArrivalTime(scheduleRequestDto.getArrivalTime());

        Schedule updatedSchedule = scheduleRepository.save(schedule);
        return mapToScheduleResponseDto(updatedSchedule);
    }

    @Transactional
    @Override
    public void deleteSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule", "scheduleId", scheduleId));
        scheduleRepository.delete(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findNextThreeBusesAtStop(Long stopId, LocalDateTime currentTime) {
        List<Schedule> nextBuses = scheduleRepository.findTop3ByBusStop_StopIdAndDepartureTimeAfterOrderByDepartureTimeAsc(stopId, currentTime);
        return nextBuses.stream()
                .map(this::mapToScheduleResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleResponseDto findNextBusForRoute(Long routeId, LocalDateTime currentTime) {
        Schedule nextBus = scheduleRepository.findTop1ByBusRoute_RouteIdAndDepartureTimeAfterOrderByDepartureTimeAsc(routeId, currentTime)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule", "routeId", routeId));
        return mapToScheduleResponseDto(nextBus);
    }

    @Override
    public Duration calculateTravelTime(Long routeId, Long stopX, Long stopY) {
        Schedule startSchedule = scheduleRepository.findTop1ByBusRoute_RouteIdAndBusStop_StopIdOrderByDepartureTimeAsc(routeId, stopX)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule", "stopId", stopX));
        Schedule endSchedule = scheduleRepository.findTop1ByBusRoute_RouteIdAndBusStop_StopIdOrderByArrivalTimeAsc(routeId, stopY)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule", "stopId", stopY));

        return Duration.between(startSchedule.getDepartureTime(), endSchedule.getArrivalTime());
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedulesOrdered() {
        List<Schedule> schedules = scheduleRepository.findAllByOrderByDepartureTimeAsc();
        return schedules.stream()
                .map(this::mapToScheduleResponseDto)
                .collect(Collectors.toList());
    }

    private ScheduleResponseDto mapToScheduleResponseDto(Schedule schedule) {
        return ScheduleResponseDto.builder()
                .scheduleId(schedule.getScheduleId())
                .departureTime(schedule.getDepartureTime())
                .arrivalTime(schedule.getArrivalTime())
                .build();
    }

}