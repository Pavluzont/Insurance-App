package com.exadel.services;

import com.exadel.dto.ClaimDto;
import com.exadel.entity.Claim;
import com.exadel.mapper.ClaimMapper;
import com.exadel.repository.ClaimRepository;
import com.exadel.services.impl.ClaimServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClaimServiceImplTest {
    public static final Long TEST_ID = 1L;

    @Mock
    private ClaimRepository claimRepository;

    @InjectMocks
    private ClaimServiceImpl claimService;

    @Mock
    private ClaimMapper claimMapper;

    @Test
    public void testFindAll() {
        List<Claim> claimsMock = new ArrayList<>();
        claimsMock.add(Claim.builder().id(1L).build());
        claimsMock.add(Claim.builder().id(2L).build());
        claimsMock.add(Claim.builder().id(3L).build());

        when(claimRepository.findAll()).thenReturn(claimsMock);

        List<ClaimDto> claims = claimService.findAll();

        assertNotNull(claims);
        assertEquals(3, claims.size());
    }

    @Test
    public void testFindById() {
        Claim claimMock = Claim.builder().id(TEST_ID).build();
        ClaimDto claimDtoMock = ClaimDto.builder().id(TEST_ID).build();

        when(claimRepository.findById(any())).thenReturn(Optional.of(claimMock));
        when(claimMapper.claimToClaimDto(any())).thenReturn(claimDtoMock);

        ClaimDto claim = claimService.findById(TEST_ID);

        assertNotNull(claim);
        assertEquals(TEST_ID, claim.getId());
    }

    @Test
    public void testFindByIdNotFound() {
        when(claimRepository.findById(anyLong())).thenReturn(Optional.empty());

        ClaimDto claim = claimService.findById(TEST_ID);

        assertNull(claim);
    }

    @Test
    public void testSave() {
        Claim claimMock = Claim.builder().build();
        ClaimDto claimDtoMock = ClaimDto.builder().build();

        when(claimRepository.save(any())).thenReturn(claimMock);
        when(claimMapper.claimDtoToClaim(any())).thenReturn(claimMock);
        when(claimMapper.claimToClaimDto(any())).thenReturn(claimDtoMock);

        ClaimDto claimDto = claimService.save(claimDtoMock);

        assertNotNull(claimDto);
        verify(claimRepository, times(1)).save(any());
    }

    @Test
    public void testDelete() {
        ClaimDto claimDtoMock = ClaimDto.builder().build();
        claimService.delete(claimDtoMock);

        verify(claimRepository, times(1)).delete(any());
    }

    @Test
    public void testDeleteById() {
        claimService.deleteById(TEST_ID);

        verify(claimRepository, times(1)).deleteById(anyLong());
    }
}