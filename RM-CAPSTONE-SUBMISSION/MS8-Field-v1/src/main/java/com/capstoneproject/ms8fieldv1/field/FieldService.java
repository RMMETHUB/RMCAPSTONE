package com.capstoneproject.ms8fieldv1.field;



import java.util.List;

public interface FieldService {
    boolean addField(FieldDTO field);
    boolean updateField(FieldDTO field);
    boolean deleteFieldById(Integer id);
    FieldDTO getFieldById(Integer id);
    Boolean validateFieldById(Integer id);
    List<FieldDTO> getAllField();

}
