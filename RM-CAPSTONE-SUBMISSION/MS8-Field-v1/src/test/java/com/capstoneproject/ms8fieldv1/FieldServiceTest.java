package com.capstoneproject.ms8fieldv1;


import com.capstoneproject.ms8fieldv1.exception.FieldException;
import com.capstoneproject.ms8fieldv1.common.Constants;
import com.capstoneproject.ms8fieldv1.field.FieldDTO;
import com.capstoneproject.ms8fieldv1.field.FieldEntity;
import com.capstoneproject.ms8fieldv1.field.FieldRepository;
import com.capstoneproject.ms8fieldv1.field.FieldServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FieldServiceTest {


    @InjectMocks
    FieldServiceImpl fieldService;

    @Mock
    FieldRepository fieldRepository;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void addFieldServiceTest(){
        FieldDTO mockFieldRequest = mockFieldRequest();
        when(fieldService.addField(mockFieldRequest)).thenReturn(true);
        boolean isSaved = fieldService.addField(mockFieldRequest());
        Assert.assertEquals(true,isSaved);
    }

    @Test
    public void addFieldServiceTestWithFieldNameShouldNotBeEmptyExceptionTest() throws Exception{
        FieldDTO mockFieldRequest = mockFieldRequest();
        mockFieldRequest.setFieldName("");
        Mockito.doThrow(new FieldException(Constants.FIELD_NAME_MANDATORY_EXCEPTION)).when(fieldRepository).saveAndFlush(any(FieldEntity.class));
        try {
            fieldService.addField(mockFieldRequest);
        } catch (FieldException ex) {
            assertEquals(Constants.FIELD_NAME_MANDATORY_EXCEPTION, ex.getMessage());
        }
    }
    @Test
    public void addFieldServiceTestWithFieldAddressShouldNotBeEmptyExceptionTest() throws Exception{
        FieldDTO mockFieldRequest = mockFieldRequest();
        mockFieldRequest.setFieldAddress("");
        doThrow(new FieldException(Constants.FIELD_ADDRESS_MANDATORY_EXCEPTION)).when(fieldRepository).saveAndFlush(any(FieldEntity.class));
        try {
            fieldService.addField(mockFieldRequest);
        } catch (FieldException ex) {
            assertEquals(Constants.FIELD_ADDRESS_MANDATORY_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void addFieldServiceTestWithFieldCapacityShouldNotBeEmptyExceptionTest() throws Exception{
        FieldDTO mockFieldRequest = mockFieldRequest();
        mockFieldRequest.setFieldCapacity(0);
        doThrow(new FieldException(Constants.FIELD_CAPACITY_MANDATORY_EXCEPTION)).when(fieldRepository).saveAndFlush(any(FieldEntity.class));
        try {
            fieldService.addField(mockFieldRequest);
        } catch (FieldException ex) {
            assertEquals(Constants.FIELD_CAPACITY_MANDATORY_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void addFieldServiceTestWithFieldAlreadyExistExceptionTest() throws Exception{
        FieldDTO mockFieldRequest = mockFieldRequest();
        when(fieldRepository.findByFieldName(anyString())).thenReturn(optionalMockFieldRequest());
        doThrow(new FieldException(Constants.FIELD_ALREADY_EXIST_EXCEPTION)).when(fieldRepository).saveAndFlush(any(FieldEntity.class));
        try {
            fieldService.addField(mockFieldRequest);
        } catch (FieldException ex) {
            assertEquals(Constants.FIELD_ALREADY_EXIST_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void updateFieldServiceTest(){
        FieldDTO mockFieldRequest = mockFieldRequest();
        when(fieldRepository.findById(anyInt())).thenReturn(optionalMockFieldRequest());
        boolean isSaved = fieldService.updateField(mockFieldRequest());
        Assert.assertEquals(true,isSaved);
    }

    @Test
    public void updateFieldServiceTestWithFieldNameShouldNotBeEmptyExceptionTest() throws Exception{
        FieldDTO mockFieldRequest = mockFieldRequest();
        mockFieldRequest.setFieldName("");
        doThrow(new FieldException(Constants.FIELD_NAME_MANDATORY_EXCEPTION)).when(fieldRepository).saveAndFlush(any(FieldEntity.class));
        try {
            fieldService.updateField(mockFieldRequest);
        } catch (FieldException ex) {
            assertEquals(Constants.FIELD_NAME_MANDATORY_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void updateFieldServiceTestWithFieldAddressShouldNotBeEmptyExceptionTest() throws Exception{
        FieldDTO mockFieldRequest = mockFieldRequest();
        mockFieldRequest.setFieldAddress("");
        doThrow(new FieldException(Constants.FIELD_ADDRESS_MANDATORY_EXCEPTION)).when(fieldRepository).saveAndFlush(any(FieldEntity.class));
        try {
            fieldService.updateField(mockFieldRequest);
        } catch (FieldException ex) {
            assertEquals(Constants.FIELD_ADDRESS_MANDATORY_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void updateFieldServiceTestWithFieldCapacityShouldNotBeEmptyExceptionTest() throws Exception{
        FieldDTO mockFieldRequest = mockFieldRequest();
        mockFieldRequest.setFieldCapacity(0);
        doThrow(new FieldException(Constants.FIELD_CAPACITY_MANDATORY_EXCEPTION)).when(fieldRepository).saveAndFlush(any(FieldEntity.class));
        try {
            fieldService.updateField(mockFieldRequest);
        } catch (FieldException ex) {
            assertEquals(Constants.FIELD_CAPACITY_MANDATORY_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void updateFieldServiceTestWithFieldAlreadyExistExceptionTest() throws Exception{
        FieldDTO mockFieldRequest = mockFieldRequest();
        when(fieldRepository.findByFieldName(anyString())).thenReturn(optionalMockFieldRequest());
        doThrow(new FieldException(Constants.FIELD_NOT_FOUND_EXCEPTION)).when(fieldRepository).saveAndFlush(any(FieldEntity.class));
        try {
            fieldService.updateField(mockFieldRequest);
        } catch (FieldException ex) {
            assertEquals(Constants.FIELD_NOT_FOUND_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void getFieldByIdTest(){
        when(fieldRepository.findByIdAndIsActive(anyInt(),anyBoolean())).thenReturn(optionalMockFieldRequest());
        FieldDTO response = fieldService.getFieldById(1);
        Assert.assertNotNull(response);
    }

    @Test
    public void getFieldByIdWithExceptionTest(){
        doThrow(new FieldException(Constants.FIELD_NOT_FOUND_EXCEPTION)).when(fieldRepository).saveAndFlush(any(FieldEntity.class));
        try {
            fieldService.getFieldById(1);
        } catch (FieldException ex) {
            assertEquals(Constants.FIELD_NOT_FOUND_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void deleteFieldTest(){
        when(fieldRepository.findByIdAndIsActive(anyInt(),anyBoolean())).thenReturn(optionalMockFieldRequest());
        boolean isDeleted =  fieldService.deleteFieldById(1);
        Assert.assertEquals(true,isDeleted);
    }

    @Test
    public void deleteFieldWithExceptionTest(){
        doThrow(new FieldException(Constants.FIELD_NOT_FOUND_EXCEPTION)).when(fieldRepository).save(any(FieldEntity.class));
        try {
            fieldService.deleteFieldById(1);
        } catch (FieldException ex) {
            assertEquals(Constants.FIELD_NOT_FOUND_EXCEPTION, ex.getMessage());
        }
    }

    @Test
    public void verifyFieldTest(){
        when(fieldRepository.findByIdAndIsActive(anyInt(),anyBoolean())).thenReturn(optionalMockFieldRequest());
        boolean isVerified =  fieldService.validateFieldById(1);
        Assert.assertEquals(true,isVerified);
    }
    @Test
    public void getAllFieldTest(){
        when(fieldRepository.findByIsActive(true)).thenReturn(mockFieldListRequest());
        List<FieldDTO> list = fieldService.getAllField();
        for (FieldDTO fieldDTO: list) {
            Assert.assertNotNull(fieldDTO.getFieldName());
            Assert.assertNotNull(fieldDTO.getFieldAddress());
        }
    }

    @Test
    public void getAllFieldWithExceptionTest(){
        doThrow(new FieldException(Constants.FIELD_NOT_FOUND_EXCEPTION)).when(fieldRepository).save(any(FieldEntity.class));
        try {
            fieldService.getAllField();
        } catch (FieldException ex) {
            assertEquals(Constants.FIELD_NOT_FOUND_EXCEPTION, ex.getMessage());
        }
    }


    private List<FieldEntity> mockFieldListRequest(){
        List<FieldEntity> newList = new ArrayList<>();
        newList.add(mockFieldEntity());
        return newList;
    }



    private FieldDTO mockFieldRequest(){
        FieldDTO fieldDTO = new FieldDTO();
        fieldDTO.setId(1);
        fieldDTO.setFieldAddress("testAddress");
        fieldDTO.setFieldName("testFieldName");
        fieldDTO.setFieldCapacity(1);
        fieldDTO.setActive(true);
        return fieldDTO;
    }

    private FieldEntity mockFieldEntity(){
        FieldEntity fieldEntity = new FieldEntity();
        fieldEntity.setId(1);
        fieldEntity.setFieldAddress("testAddress");
        fieldEntity.setFieldName("testFieldName");
        fieldEntity.setFieldCapacity(1);
        fieldEntity.setActive(true);
        return fieldEntity;
    }

    private Optional<FieldEntity> optionalMockFieldRequest(){
        Optional<FieldEntity> optionalFieldDTO = Optional.of(mockFieldEntity());
        return optionalFieldDTO;
    }



}
