package com.capstoneproject.ms8fieldv1;

import com.capstoneproject.ms8fieldv1.field.FieldEntity;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FieldEntityTest {

    @Test
    public void initializingFieldEntityTest(){
        FieldEntity fieldEntity = mockFieldEntity();
        FieldEntity expectedResult = new FieldEntity(1,"test","testAddress",1,true);
        Assert.assertEquals(expectedResult.getId(), fieldEntity.getId());
        Assert.assertEquals(expectedResult.getFieldName(), fieldEntity.getFieldName());
        Assert.assertEquals(expectedResult.getFieldAddress(), fieldEntity.getFieldAddress());
        Assert.assertEquals(expectedResult.getFieldCapacity(), fieldEntity.getFieldCapacity());
        Assert.assertEquals(expectedResult.isActive(), fieldEntity.isActive());
    }

    @Test(expected = NullPointerException.class)
    public void whenFieldIdIsNull_return_an_Exception(){
        FieldEntity fieldEntity  = new FieldEntity();
        fieldEntity.setId((Integer)null);
    }
    @Test
    public void toStringTest(){
        String fieldEntityString = "FieldEntity{id=1, fieldName='test', fieldAddress='testAddress', fieldCapacity=1, isActive=true}";
        FieldEntity field = new FieldEntity(1,"test","testAddress",1,true);
        Assert.assertEquals(fieldEntityString,field.toString());
    }

    private FieldEntity mockFieldEntity(){
        FieldEntity fieldEntity = new FieldEntity();
        fieldEntity.setId(1);
        fieldEntity.setFieldName("test");
        fieldEntity.setFieldCapacity(1);
        fieldEntity.setFieldAddress("testAddress");
        fieldEntity.setActive(true);
        return fieldEntity;
    }

}
