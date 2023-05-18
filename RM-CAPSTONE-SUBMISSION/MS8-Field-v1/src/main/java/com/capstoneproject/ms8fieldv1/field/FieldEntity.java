package com.capstoneproject.ms8fieldv1.field;

;

import javax.persistence.*;


@Entity
@Table(name= "field",schema = "field_schema")
public class FieldEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_id")
    private int id;
    @Column(name = "field_name")
    private String fieldName;
    @Column(name = "field_address")
    private String fieldAddress;
    @Column(name = "field_capacity")
    private int fieldCapacity;
    @Column(name = "isActive")
    private boolean isActive;

    public FieldEntity() {
    }

    public FieldEntity(String fieldName, String fieldAddress, int fieldCapacity, boolean isActive) {
        this.fieldName = fieldName;
        this.fieldAddress = fieldAddress;
        this.fieldCapacity = fieldCapacity;
        this.isActive = isActive;
    }

    public FieldEntity(int id, String fieldName, String fieldAddress, int fieldCapacity, boolean isActive) {
        this.id = id;
        this.fieldName = fieldName;
        this.fieldAddress = fieldAddress;
        this.fieldCapacity = fieldCapacity;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldAddress() {
        return fieldAddress;
    }

    public void setFieldAddress(String fieldAddress) {
        this.fieldAddress = fieldAddress;
    }

    public int getFieldCapacity() {
        return fieldCapacity;
    }

    public void setFieldCapacity(int fieldCapacity) {
        this.fieldCapacity = fieldCapacity;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "FieldEntity{" +
                "id=" + id +
                ", fieldName='" + fieldName + '\'' +
                ", fieldAddress='" + fieldAddress + '\'' +
                ", fieldCapacity=" + fieldCapacity +
                ", isActive=" + isActive +
                '}';
    }
}
