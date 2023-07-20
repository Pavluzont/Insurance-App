package com.exadel.controllers;

import com.exadel.dto.ContractDto;
import com.exadel.services.ContractService;
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
@RequestMapping("/contracts")
@RequiredArgsConstructor
@Tag(name = "Contracts")
public class ContractController {
    private final ContractService contractService;

    @GetMapping
    public List<ContractDto> findAll() {
        log.info("Find all contracts");
        return contractService.findAll();
    }

    @GetMapping("/{contractId}")
    @Operation(description = "Get endpoint for contract", summary = "This is a summary for contract endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "contract not found", responseCode = "404")
    })
    public ContractDto getContract(@PathVariable Long contractId) {
        log.info("Get contract by id={}", contractId);
        contractService.validate(contractId);
        return contractService.findById(contractId);
    }

    @PostMapping
    @Operation(description = "Save a new contract", summary = "This is a summary for contract endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "contract was successfully added", responseCode = "201"),
    })
    public ContractDto addContract(@RequestBody ContractDto contractDto) {
        log.info("Save contract");
        ContractDto dbContract = contractService.save(contractDto);

        return dbContract;
    }

    @PutMapping
    @Operation(description = "Get update for contract", summary = "This is a summary for contract endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "Success update", responseCode = "202"),
            @ApiResponse(description = "contract not found", responseCode = "404")
    })
    public ContractDto updateContract(@RequestBody ContractDto contractDto) {
        log.info("Update contract");
        return contractService.save(contractDto);
    }

    private void deleteContract(@RequestBody ContractDto contractDto) {
        log.info("Delete contract");
        contractService.delete(contractDto);
    }

    @DeleteMapping("/{contractId}")
    @Operation(description = "Delete a contract", summary = "This is a summary for contract endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "contract was successfully deleted", responseCode = "204"),
            @ApiResponse(description = "contract not found", responseCode = "404")
    })
    public void deleteContractById(@PathVariable Long contractId) {
        log.info("Delete contract by id id={}", contractId);
        contractService.validate(contractId);
        contractService.deleteById(contractId);
    }

    @DeleteMapping
    @Operation(description = "Delete all contracts", summary = "This is a summary for contracts endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "contracts were successfully deleted", responseCode = "204")
    })
    public void deleteAll() {
        log.info("Delete all contracts");
        contractService.deleteAll();
    }
}