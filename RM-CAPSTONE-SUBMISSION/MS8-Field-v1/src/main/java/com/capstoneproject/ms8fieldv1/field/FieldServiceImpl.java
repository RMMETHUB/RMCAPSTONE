package com.capstoneproject.ms8fieldv1.field;


import com.capstoneproject.ms8fieldv1.common.Constants;
import com.capstoneproject.ms8fieldv1.exception.FieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class FieldServiceImpl implements FieldService{
    @Autowired
    FieldRepository fieldRepository;
    @Override
    public boolean addField(FieldDTO field){
        Optional<FieldEntity> findByFieldName = fieldRepository.findByFieldName(field.getFieldName());
        if (field.getFieldName().isEmpty()){
            throw new FieldException(Constants.FIELD_NAME_MANDATORY_EXCEPTION);
        } else if (field.getFieldAddress().isEmpty()) {
            throw new FieldException(Constants.FIELD_ADDRESS_MANDATORY_EXCEPTION);
        } else if (field.getFieldCapacity() == 0) {
            throw new FieldException(Constants.FIELD_CAPACITY_MANDATORY_EXCEPTION);
        } else if(findByFieldName.isPresent()){
            throw new FieldException(Constants.FIELD_ALREADY_EXIST_EXCEPTION);
        }else{
            fieldRepository.save(new FieldEntity(field.getFieldName(),field.getFieldAddress(),field.getFieldCapacity(),true));
            return true;
        }
    }

    @Override
    public boolean updateField(FieldDTO field){
        Optional<FieldEntity> findByFieldName = fieldRepository.findById(field.getId());
        if (field.getFieldName().isEmpty()){
            throw new FieldException(Constants.FIELD_NAME_MANDATORY_EXCEPTION);
        } else if (field.getFieldAddress().isEmpty()) {
            throw new FieldException(Constants.FIELD_ADDRESS_MANDATORY_EXCEPTION);
        } else if (field.getFieldCapacity() == 0) {
            throw new FieldException(Constants.FIELD_CAPACITY_MANDATORY_EXCEPTION);
        } else if(findByFieldName.isEmpty()){
            throw new FieldException(Constants.FIELD_NOT_FOUND_EXCEPTION);
        }else{
            findByFieldName.get().setFieldName(field.getFieldName());
            findByFieldName.get().setFieldAddress(field.getFieldAddress());
            findByFieldName.get().setFieldCapacity(field.getFieldCapacity());
            fieldRepository.save(findByFieldName.get());
            return true;
        }
    }

    @Override
    public boolean deleteFieldById(Integer id){
        Optional<FieldEntity> findById = fieldRepository.findByIdAndIsActive(id,true);
        if(findById.isEmpty()){
            throw new FieldException(Constants.FIELD_NOT_FOUND_EXCEPTION);
        }else{
            findById.get().setActive(false);
            fieldRepository.save(findById.get());
            return true;
        }
    }

    @Override
    public FieldDTO getFieldById(Integer id){
        Optional<FieldEntity> findFieldById = fieldRepository.findByIdAndIsActive(id,true);
        FieldDTO fieldDTO = new FieldDTO();
        if (findFieldById.isPresent()){
            fieldDTO.setId(findFieldById.get().getId());
            fieldDTO.setFieldName(findFieldById.get().getFieldName());
            fieldDTO.setFieldCapacity(findFieldById.get().getFieldCapacity());
            fieldDTO.setFieldAddress(findFieldById.get().getFieldAddress());
            fieldDTO.setActive(findFieldById.get().isActive());
            return fieldDTO;
        }else {
            throw new FieldException(Constants.FIELD_NOT_FOUND_EXCEPTION);
        }
    }

    @Override
    public Boolean validateFieldById(Integer id){
        Optional<FieldEntity> findFieldById = fieldRepository.findByIdAndIsActive(id,true);
        return findFieldById.isPresent();
    }

    @Override
    public List<FieldDTO> getAllField() {
        List<FieldEntity> getAllField =  fieldRepository.findByIsActive(true);
        List<FieldDTO> fieldDTOS = new ArrayList<>();
        if (getAllField != null){
            for (FieldEntity fieldEntity: getAllField) {
                FieldDTO requestModel = new FieldDTO();
                requestModel.setId(fieldEntity.getId());
                requestModel.setFieldCapacity(fieldEntity.getFieldCapacity());
                requestModel.setFieldName(fieldEntity.getFieldName());
                requestModel.setFieldAddress(fieldEntity.getFieldAddress());
                requestModel.setActive(fieldEntity.isActive());
                fieldDTOS.add(requestModel);
            }
        }
        return fieldDTOS;
    }
}
