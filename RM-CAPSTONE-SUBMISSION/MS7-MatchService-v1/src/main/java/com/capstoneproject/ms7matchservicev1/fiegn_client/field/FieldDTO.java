package com.capstoneproject.ms7matchservicev1.fiegn_client.field;

public class FieldDTO {

    private String fieldName;
    private String fieldAddress;
    private int fieldCapacity;
    private boolean isActive;

    public FieldDTO() {
    }

    public FieldDTO(String fieldName, String fieldAddress, int fieldCapacity) {
        this.fieldName = fieldName;
        this.fieldAddress = fieldAddress;
        this.fieldCapacity = fieldCapacity;
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
}
