package com.capstoneproject.ms8fieldv1;

import com.capstoneproject.ms8fieldv1.field.FieldController;
import com.capstoneproject.ms8fieldv1.field.FieldDTO;
import com.capstoneproject.ms8fieldv1.field.FieldServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
public class FieldControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    FieldController fieldController;

    @Mock
    FieldServiceImpl fieldService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(fieldController).setCustomArgumentResolvers().build();
    }
    @Test
    public void savePlayerTest() throws Exception{
        when(fieldService.addField(any(FieldDTO.class))).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8081/field/v1/addField")
                        .contentType("application/json")
                        .content(asJsonString(mockFieldRequest())))
                .andExpect(status().is(201));
    }

    @Test
    public void updatePlayerTest() throws Exception{
        when(fieldService.updateField(any(FieldDTO.class))).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8082/field/v1/updateField")
                        .contentType("application/json")
                        .content(asJsonString(mockFieldRequest())))
                .andExpect(status().is(200));
    }

    @Test
    public void deleteFieldTest() throws Exception{
        when(fieldService.deleteFieldById(anyInt())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8082/field/v1/deleteField?id="+1)
                        .contentType("application/json")
                        .content(asJsonString(mockFieldRequest())))
                .andExpect(status().is(200));
    }

    @Test
    public void getAllPlayerTest() throws Exception{
        when(fieldService.getAllField()).thenReturn(mockFieldListRequest());
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/field/v1/getField"))
                .andExpect(status().isOk());
    }

    @Test
    public void validateById() throws Exception{
        when(fieldService.validateFieldById(anyInt())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/field/v1/validateFieldById?id="+1))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
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
    private List<FieldDTO> mockFieldListRequest(){
        List<FieldDTO> newList = new ArrayList<>();
        newList.add(mockFieldRequest());
        return newList;
    }

    private static String  asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
