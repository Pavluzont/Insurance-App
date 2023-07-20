package com.exadel.controllers;

import com.exadel.dto.OwnerDto;
import com.exadel.services.OwnerService;
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
@RequestMapping("/owners")
@RequiredArgsConstructor
@Tag(name = "Owners")
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping
    public List<OwnerDto> findAll() {
        log.info("Find all owners");
        return ownerService.findAll();
    }

    @GetMapping("/{ownerId}")
    @Operation(description = "Get endpoint for owner", summary = "This is a summary for owner endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "owner not found", responseCode = "404")
    })
    public OwnerDto getOwner(@PathVariable Long ownerId) {
        log.info("Get owner by id={}", ownerId);
        ownerService.validate(ownerId);
        return ownerService.findById(ownerId);
    }

    @GetMapping("/uid/{ownerUid}")
    public OwnerDto getOwnerByUid(@PathVariable String ownerUid) {
        log.info("Get owner by id={}", ownerUid);

        return ownerService.findByUid(ownerUid);
    }

    @PostMapping
    @Operation(description = "Save a new owner", summary = "This is a summary for owner endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "owner was successfully added", responseCode = "201"),
    })
    public OwnerDto addOwner(@RequestBody OwnerDto ownerDto) {
        log.info("Save owner");
        OwnerDto dbOwner = ownerService.save(ownerDto);

        return dbOwner;
    }

    @PutMapping
    @Operation(description = "Get update for owner", summary = "This is a summary for owner endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "Success update", responseCode = "202"),
            @ApiResponse(description = "owner not found", responseCode = "404")
    })
    public OwnerDto updateOwner(@RequestBody OwnerDto ownerDto) {
        log.info("Update owner");
        return ownerService.save(ownerDto);
    }

    private void deleteOwner(@RequestBody OwnerDto ownerDto) {
        log.info("Delete owner");
        ownerService.delete(ownerDto);
    }

    @DeleteMapping("/{ownerId}")
    @Operation(description = "Delete a owner", summary = "This is a summary for owner endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "owner was successfully deleted", responseCode = "204"),
            @ApiResponse(description = "owner not found", responseCode = "404")
    })
    public void deleteOwnerById(@PathVariable Long ownerId) {
        log.info("Delete owner by id id={}", ownerId);
        ownerService.validate(ownerId);
        ownerService.deleteById(ownerId);
    }

    @DeleteMapping
    @Operation(description = "Delete all owners", summary = "This is a summary for owners endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "owners were successfully deleted", responseCode = "204")
    })
    public void deleteAll() {
        log.info("Delete all owners");
        ownerService.deleteAll();
    }
}
