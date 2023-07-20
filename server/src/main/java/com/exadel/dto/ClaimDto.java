package com.exadel.dto;

import com.exadel.entity.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClaimDto {
    @Setter(AccessLevel.NONE)
    private Long id;

    private CarDto car;
    private ContractDto contract;

    private Instant createdDate;
}
