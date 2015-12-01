package edu.wright.hendrix11.familyTree.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converts between boolean and integer: {@code 1} is {@code true} and {@code 0} is {@code false}.
 * 
 * @author Joe
 */
@Converter(autoApply = true)
public class BooleanConverter implements AttributeConverter<Boolean, Integer>
{

    /**
     * Converts {@code true} to {@code 1}, {@code false} to {@code 0}, and {@code null} to {@code null}.
     *
     * @param value the value to be converted
     *
     * @return an {@code Integer} representation of a {@code Boolean} value
     */
    @Override
    public Integer convertToDatabaseColumn(Boolean value)
    {
        if ( value == null )
            return null;
        else if ( value )
            return 1;
        else
            return 0;
    }

    /**
     * Converts {@code 1} to {@code true}, {@code 0} to {@code false}, and {@code null} to {@code null}.
     *
     * @param value the value to be converted
     *
     * @return a {@code Boolean} representation of an {@code Integer} value
     */
    @Override
    public Boolean convertToEntityAttribute(Integer value)
    {
        if ( value == null )
            return null;
        else
            return !value.equals(0);
    }
}
