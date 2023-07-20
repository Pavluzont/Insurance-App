package com.exadel.mapper;

import com.exadel.dto.DocumentDto;
import com.exadel.entity.Document;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DocumentMapper {
    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    DocumentDto documentToDocumentDto(Document Document);

    Document documentDtoToDocument(DocumentDto DocumentDto);
}
