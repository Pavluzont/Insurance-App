package com.exadel.controllers;

import com.exadel.dto.InsuranceCompanyDto;
import com.exadel.services.InsuranceCompanyService;
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
@RequestMapping("/companies")
@RequiredArgsConstructor
@Tag(name = "Insurance Companies")
public class InsuranceCompanyController {
    private final InsuranceCompanyService insuranceCompanyService;

    @GetMapping
    public List<InsuranceCompanyDto> findAll() {
        log.info("Find all companies");
        return insuranceCompanyService.findAll();
    }

    @GetMapping("/{companyId}")
    @Operation(description = "Get endpoint for company", summary = "This is a summary for company endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "company not found", responseCode = "404")
    })
    public InsuranceCompanyDto getCompany(@PathVariable Long companyId) {
        log.info("Get company by id={}", companyId);
        insuranceCompanyService.validate(companyId);
        return insuranceCompanyService.findById(companyId);
    }

    @PostMapping
    @Operation(description = "Save a new company", summary = "This is a summary for company endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "company was successfully added", responseCode = "201"),
    })
    public InsuranceCompanyDto addCompany(@RequestBody InsuranceCompanyDto insuranceCompanyDto) {
        log.info("Save company");
        InsuranceCompanyDto dbCompany = insuranceCompanyService.save(insuranceCompanyDto);

        return dbCompany;
    }

    @PutMapping
    @Operation(description = "Get update for company", summary = "This is a summary for company endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "Success update", responseCode = "202"),
            @ApiResponse(description = "company not found", responseCode = "404")
    })
    public InsuranceCompanyDto updateCompany(@RequestBody InsuranceCompanyDto insuranceCompanyDto) {
        log.info("Update company");
        return insuranceCompanyService.save(insuranceCompanyDto);
    }

    private void deleteCompany(@RequestBody InsuranceCompanyDto insuranceCompanyDto) {
        log.info("Delete company");
        insuranceCompanyService.delete(insuranceCompanyDto);
    }

    @DeleteMapping("/{companyId}")
    @Operation(description = "Delete a company", summary = "This is a summary for company endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "company was successfully deleted", responseCode = "204"),
            @ApiResponse(description = "company not found", responseCode = "404")
    })
    public void deleteCompanyById(@PathVariable Long companyId) {
        log.info("Delete company by id id={}", companyId);
        insuranceCompanyService.validate(companyId);
        insuranceCompanyService.deleteById(companyId);
    }

    @DeleteMapping
    @Operation(description = "Delete all companies", summary = "This is a summary for companies endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "companies were successfully deleted", responseCode = "204")
    })
    public void deleteAll() {
        log.info("Delete all companies");
        insuranceCompanyService.deleteAll();
    }
}