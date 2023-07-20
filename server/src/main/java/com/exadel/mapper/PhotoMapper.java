package com.exadel.mapper;

import com.exadel.dto.PhotoDto;
import com.exadel.entity.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PhotoMapper {
    PhotoMapper INSTANCE = Mappers.getMapper(PhotoMapper.class);

    PhotoDto photoToPhotoDto(Photo Photo);

    Photo photoDtoToPhoto(PhotoDto PhotoDto);
}
