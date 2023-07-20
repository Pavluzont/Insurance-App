package com.exadel.services;

import com.exadel.dto.OwnerDto;
import com.exadel.entity.Owner;
import com.exadel.mapper.OwnerMapper;
import com.exadel.repository.OwnerRepository;
import com.exadel.services.impl.OwnerServiceImpl;
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
public class OwnerServiceImplTest {
    public static final Long TEST_ID = 1L;

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerServiceImpl ownerService;

    @Mock
    private OwnerMapper ownerMapper;

    @Test
    public void testFindAll() {
        List<Owner> ownersMock = new ArrayList<>();
        ownersMock.add(Owner.builder().id(1L).build());
        ownersMock.add(Owner.builder().id(2L).build());
        ownersMock.add(Owner.builder().id(3L).build());

        when(ownerRepository.findAll()).thenReturn(ownersMock);

        List<OwnerDto> owners = ownerService.findAll();

        assertNotNull(owners);
        assertEquals(3, owners.size());
    }

    @Test
    public void testFindById() {
        Owner ownerMock = Owner.builder().id(TEST_ID).build();
        OwnerDto ownerDtoMock = OwnerDto.builder().id(TEST_ID).build();

        when(ownerRepository.findById(any())).thenReturn(Optional.of(ownerMock));
        when(ownerMapper.ownerToOwnerDto(any())).thenReturn(ownerDtoMock);

        OwnerDto owner = ownerService.findById(TEST_ID);

        assertNotNull(owner);
        assertEquals(TEST_ID, owner.getId());
    }

    @Test
    public void testFindByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        OwnerDto owner = ownerService.findById(TEST_ID);

        assertNull(owner);
    }

    @Test
    public void testSave() {
        Owner ownerMock = Owner.builder().build();
        OwnerDto ownerDtoMock = OwnerDto.builder().build();

        when(ownerRepository.save(any())).thenReturn(ownerMock);
        when(ownerMapper.ownerDtoToOwner(any())).thenReturn(ownerMock);
        when(ownerMapper.ownerToOwnerDto(any())).thenReturn(ownerDtoMock);

        OwnerDto ownerDto = ownerService.save(ownerDtoMock);

        assertNotNull(ownerDto);
        verify(ownerRepository, times(1)).save(any());
    }

    @Test
    public void testDelete() {
        OwnerDto ownerDtoMock = OwnerDto.builder().build();
        ownerService.delete(ownerDtoMock);

        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    public void testDeleteById() {
        ownerService.deleteById(TEST_ID);

        verify(ownerRepository, times(1)).deleteById(anyLong());
    }
}
