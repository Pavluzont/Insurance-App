package com.exadel.dto;

import com.exadel.entity.Car;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentDto {
    @Setter(AccessLevel.NONE)
    private Long id;
    private String name;
    private BigDecimal amount;
    private LocalDate claimIssueDate;
    private Instant createdDate;

    private CarDto car;

    private List<PhotoDto> photos;
}
