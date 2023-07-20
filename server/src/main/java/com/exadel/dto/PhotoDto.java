package com.exadel.dto;

import com.exadel.entity.Document;
import lombok.*;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoDto {
    @Setter(AccessLevel.NONE)
    private Long id;
    private String name;
    private byte[] picture;
    private Instant createdDate;
    private DocumentDto document;
}
