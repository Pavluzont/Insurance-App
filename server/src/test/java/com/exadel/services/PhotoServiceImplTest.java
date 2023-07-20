package com.exadel.services;

import com.exadel.dto.PhotoDto;
import com.exadel.entity.Photo;
import com.exadel.mapper.PhotoMapper;
import com.exadel.repository.PhotoRepository;
import com.exadel.services.impl.PhotoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PhotoServiceImplTest {
    public static final Long TEST_ID = 1L;

    @Mock
    private PhotoRepository photoRepository;

    @InjectMocks
    private PhotoServiceImpl photoService;

    @Mock
    private PhotoMapper photoMapper;

    @Test
    public void testFindAll() {
        List<Photo> photosMock = new ArrayList<>();
        photosMock.add(Photo.builder().id(1L).build());
        photosMock.add(Photo.builder().id(2L).build());
        photosMock.add(Photo.builder().id(3L).build());

        when(photoRepository.findAll()).thenReturn(photosMock);

        List<PhotoDto> photos = photoService.findAll();

        assertNotNull(photos);
        assertEquals(3, photos.size());
    }

    @Test
    public void testFindById() {
        Photo photoMock = Photo.builder().id(TEST_ID).build();
        PhotoDto photoDtoMock = PhotoDto.builder().id(TEST_ID).build();

        when(photoRepository.findById(any())).thenReturn(Optional.of(photoMock));
        when(photoMapper.photoToPhotoDto(any())).thenReturn(photoDtoMock);

        PhotoDto photo = photoService.findById(TEST_ID);

        assertNotNull(photo);
        assertEquals(TEST_ID, photo.getId());
    }

    @Test
    public void testFindByIdNotFound() {
        when(photoRepository.findById(anyLong())).thenReturn(Optional.empty());

        PhotoDto photo = photoService.findById(TEST_ID);

        assertNull(photo);
    }

    @Test
    public void testSave() {
        Photo photoMock = Photo.builder().build();
        PhotoDto photoDtoMock = PhotoDto.builder().build();

        when(photoRepository.save(any())).thenReturn(photoMock);
        when(photoMapper.photoDtoToPhoto(any())).thenReturn(photoMock);
        when(photoMapper.photoToPhotoDto(any())).thenReturn(photoDtoMock);

        PhotoDto photoDto = photoService.save(photoDtoMock);

        assertNotNull(photoDto);
        verify(photoRepository, times(1)).save(any());
    }

    @Test
    public void testDelete() {
        PhotoDto photoDtoMock = PhotoDto.builder().build();
        photoService.delete(photoDtoMock);

        verify(photoRepository, times(1)).delete(any());
    }

    @Test
    public void testDeleteById() {
        photoService.deleteById(TEST_ID);

        verify(photoRepository, times(1)).deleteById(anyLong());
    }
}
