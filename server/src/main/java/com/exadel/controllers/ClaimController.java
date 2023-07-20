package com.exadel.controllers;

import com.exadel.dto.ClaimDto;
import com.exadel.services.ClaimService;
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
@RequestMapping("/claims")
@RequiredArgsConstructor
@Tag(name = "Claims")
public class ClaimController {
    private final ClaimService claimService;

    @GetMapping
    public List<ClaimDto> findAll() {
        log.info("Find all claims");
        return claimService.findAll();
    }

    @GetMapping("/{claimId}")
    @Operation(description = "Get endpoint for claim", summary = "This is a summary for claim endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "claim not found", responseCode = "404")
    })
    public ClaimDto getClaim(@PathVariable Long claimId) {
        log.info("Get claim by id={}", claimId);
        claimService.validate(claimId);
        return claimService.findById(claimId);
    }

    @PostMapping
    @Operation(description = "Save a new claim", summary = "This is a summary for claim endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "claim was successfully added", responseCode = "201"),
    })
    public ClaimDto addClaim(@RequestBody ClaimDto claimDto) {
        log.info("Save claim");
        ClaimDto dbClaim = claimService.save(claimDto);

        return dbClaim;
    }

    @PutMapping
    @Operation(description = "Get update for claim", summary = "This is a summary for claim endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "Success update", responseCode = "202"),
            @ApiResponse(description = "claim not found", responseCode = "404")
    })
    public ClaimDto updateClaim(@RequestBody ClaimDto claimDto) {
        log.info("Update claim");
        return claimService.save(claimDto);
    }

    private void deleteClaim(@RequestBody ClaimDto claimDto) {
        log.info("Delete claim");
        claimService.delete(claimDto);
    }

    @DeleteMapping("/{claimId}")
    @Operation(description = "Delete a claim", summary = "This is a summary for claim endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "claim was successfully deleted", responseCode = "204"),
            @ApiResponse(description = "claim not found", responseCode = "404")
    })
    public void deleteClaimById(@PathVariable Long claimId) {
        log.info("Delete claim by id id={}", claimId);
        claimService.validate(claimId);
        claimService.deleteById(claimId);
    }

    @DeleteMapping
    @Operation(description = "Delete all claims", summary = "This is a summary for claims endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "claims were successfully deleted", responseCode = "204")
    })
    public void deleteAll() {
        log.info("Delete all claims");
        claimService.deleteAll();
    }
}
