package com.capstoneproject.ms8fieldv1.field;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface FieldRepository extends JpaRepository<FieldEntity,Integer> {

    Optional<FieldEntity> findByFieldName(String fieldName);

    Optional<FieldEntity> findByIdAndIsActive(Integer id,boolean isActive);
    List<FieldEntity> findByIsActive(boolean isActive);
}
