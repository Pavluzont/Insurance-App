package com.exadel.services;

import com.exadel.dto.ContractDto;
import com.exadel.entity.Contract;
import com.exadel.mapper.ContractMapper;
import com.exadel.repository.ContractRepository;
import com.exadel.services.impl.ContractServiceImpl;
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
public class ContractServiceImplTest {
    public static final Long TEST_ID = 1L;

    @Mock
    private ContractRepository contractRepository;

    @InjectMocks
    private ContractServiceImpl contractService;

    @Mock
    private ContractMapper contractMapper;

    @Test
    public void testFindAll() {
        List<Contract> contractsMock = new ArrayList<>();
        contractsMock.add(Contract.builder().id(1L).build());
        contractsMock.add(Contract.builder().id(2L).build());
        contractsMock.add(Contract.builder().id(3L).build());

        when(contractRepository.findAll()).thenReturn(contractsMock);

        List<ContractDto> contracts = contractService.findAll();

        assertNotNull(contracts);
        assertEquals(3, contracts.size());
    }

    @Test
    public void testFindById() {
        Contract contractMock = Contract.builder().id(TEST_ID).build();
        ContractDto contractDtoMock = ContractDto.builder().id(TEST_ID).build();

        when(contractRepository.findById(any())).thenReturn(Optional.of(contractMock));
        when(contractMapper.contractToContractDto(any())).thenReturn(contractDtoMock);

        ContractDto contract = contractService.findById(TEST_ID);

        assertNotNull(contract);
        assertEquals(TEST_ID, contract.getId());
    }

    @Test
    public void testFindByIdNotFound() {
        when(contractRepository.findById(anyLong())).thenReturn(Optional.empty());

        ContractDto contract = contractService.findById(TEST_ID);

        assertNull(contract);
    }

    @Test
    public void testSave() {
        Contract contractMock = Contract.builder().build();
        ContractDto contractDtoMock = ContractDto.builder().build();

        when(contractRepository.save(any())).thenReturn(contractMock);
        when(contractMapper.contractDtoToContract(any())).thenReturn(contractMock);
        when(contractMapper.contractToContractDto(any())).thenReturn(contractDtoMock);

        ContractDto contractDto = contractService.save(contractDtoMock);

        assertNotNull(contractDto);
        verify(contractRepository, times(1)).save(any());
    }

    @Test
    public void testDelete() {
        ContractDto contractDtoMock = ContractDto.builder().build();
        contractService.delete(contractDtoMock);

        verify(contractRepository, times(1)).delete(any());
    }

    @Test
    public void testDeleteById() {
        contractService.deleteById(TEST_ID);

        verify(contractRepository, times(1)).deleteById(anyLong());
    }
}

