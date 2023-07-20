package com.exadel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "photos")
public class Photo extends BaseEntity {
    private String name;

    private byte[] picture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    //@JsonBackReference(value="document-photo")
    private Document document;
}
