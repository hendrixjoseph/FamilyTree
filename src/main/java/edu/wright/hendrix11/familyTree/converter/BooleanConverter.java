package edu.wright.hendrix11.familyTree.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BooleanConverter implements AttributeConverter<Boolean, Integer>
{
    @Override
    public Integer convertToDatabaseColumn(Boolean value)
    {
        if ( value )
            return 1;
        else
            return 0;
    }

    @Override
    public Boolean convertToEntityAttribute(Integer value)
    {
        if ( value == null )
            return null;
        else
            return !value.equals(0);
    }
}
