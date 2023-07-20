package com.exadel.controllers;

import com.exadel.dto.PlanDto;
import com.exadel.services.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
@Tag(name = "Plans")
public class PlanController {
    private final PlanService planService;

    @GetMapping
    public List<PlanDto> findAll() {
        log.info("Find all plans");
        return planService.findAll();
    }

    @GetMapping("/{planId}")
    @Operation(description = "Get endpoint for plan", summary = "This is a summary for plan endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "plan not found", responseCode = "404")
    })
    public PlanDto getPlan(@PathVariable Long planId) {
        log.info("Get plan by id={}", planId);
        planService.validate(planId);
        return planService.findById(planId);
    }

    @PostMapping
    @Operation(description = "Save a new plan", summary = "This is a summary for plan endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "plan was successfully added", responseCode = "201"),
    })
    public PlanDto addPlan(@RequestBody PlanDto planDto) {
        log.info("Save plan");
        PlanDto dbPlan = planService.save(planDto);

        return dbPlan;
    }

    @PutMapping
    @Operation(description = "Get update for plan", summary = "This is a summary for plan endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "Success update", responseCode = "202"),
            @ApiResponse(description = "plan not found", responseCode = "404")
    })
    public PlanDto updatePlan(@RequestBody PlanDto planDto) {
        log.info("Update plan");
        return planService.save(planDto);
    }

    private void deletePlan(@RequestBody PlanDto planDto) {
        log.info("Delete plan");
        planService.delete(planDto);
    }

    @DeleteMapping("/{planId}")
    @Operation(description = "Delete a plan", summary = "This is a summary for plan endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "plan was successfully deleted", responseCode = "204"),
            @ApiResponse(description = "plan not found", responseCode = "404")
    })
    public void deletePlanById(@PathVariable Long planId) {
        log.info("Delete plan by id id={}", planId);
        planService.validate(planId);
        planService.deleteById(planId);
    }

    @DeleteMapping
    @Operation(description = "Delete all plans", summary = "This is a summary for plans endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "plans were successfully deleted", responseCode = "204")
    })
    public void deleteAll() {
        log.info("Delete all plans");
        planService.deleteAll();
    }
}
