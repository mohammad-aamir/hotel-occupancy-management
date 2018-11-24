package tech.mohammad.amir.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.mohammad.amir.dto.OccupancyRequestDTO;
import tech.mohammad.amir.dto.OccupancyResponseDTO;
import tech.mohammad.amir.service.OccupancyOptimizationService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(description = "Provides endpoint related to Hotel Room Occupancy Optimization")
@RestController
@RequestMapping(value = {"", "/v1"})
@RequiredArgsConstructor
public class OccupancyOptimizationController {
    private final OccupancyOptimizationService occupancyOptimizationService;

    @ApiOperation("This endpoint optimize the Hotel Room Occupancy")
    @PostMapping(value = "/optimizeOccupancy", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public OccupancyResponseDTO optimizeOccupancy(@RequestBody OccupancyRequestDTO occupancyRequest) {
        return occupancyOptimizationService.optimizeOccupancy(occupancyRequest);
    }
}
