package com.exadel.controllers;

import com.exadel.dto.ConsultantDto;
import com.exadel.services.ConsultantService;
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
@RequestMapping("/consultants")
@RequiredArgsConstructor
@Tag(name = "Consultants")
public class ConsultantController {
    private final ConsultantService consultantService;

    @GetMapping
    public List<ConsultantDto> findAll() {
        log.info("Find all consultants");
        return consultantService.findAll();
    }

    @GetMapping("/{consultantId}")
    @Operation(description = "Get endpoint for consultant", summary = "This is a summary for consultant endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "consultant not found", responseCode = "404")
    })
    public ConsultantDto getConsultant(@PathVariable Long consultantId) {
        log.info("Get consultant by id={}", consultantId);
        consultantService.validate(consultantId);
        return consultantService.findById(consultantId);
    }

    @PostMapping
    @Operation(description = "Save a new consultant", summary = "This is a summary for consultant endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "consultant was successfully added", responseCode = "201"),
    })
    public ConsultantDto addConsultant(@RequestBody ConsultantDto consultantDto) {
        log.info("Save consultant");
        ConsultantDto dbConsultant = consultantService.save(consultantDto);

        return dbConsultant;
    }

    @PutMapping
    @Operation(description = "Get update for consultant", summary = "This is a summary for consultant endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "Success update", responseCode = "202"),
            @ApiResponse(description = "consultant not found", responseCode = "404")
    })
    public ConsultantDto updateConsultant(@RequestBody ConsultantDto consultantDto) {
        log.info("Update consultant");
        return consultantService.save(consultantDto);
    }

    private void deleteConsultant(@RequestBody ConsultantDto consultantDto) {
        log.info("Delete consultant");
        consultantService.delete(consultantDto);
    }

    @DeleteMapping("/{consultantId}")
    @Operation(description = "Delete a consultant", summary = "This is a summary for consultant endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "consultant was successfully deleted", responseCode = "204"),
            @ApiResponse(description = "consultant not found", responseCode = "404")
    })
    public void deleteConsultantById(@PathVariable Long consultantId) {
        log.info("Delete consultant by id id={}", consultantId);
        consultantService.validate(consultantId);
        consultantService.deleteById(consultantId);
    }

    @DeleteMapping
    @Operation(description = "Delete all consultants", summary = "This is a summary for consultants endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "consultants were successfully deleted", responseCode = "204")
    })
    public void deleteAll() {
        log.info("Delete all consultants");
        consultantService.deleteAll();
    }
}