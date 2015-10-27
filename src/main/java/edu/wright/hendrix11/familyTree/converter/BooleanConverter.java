@Converter(autoApply=true)
public class BooleanConverter implements AttributeConverter<Boolean, Integer>
{
    @Override
    public String convertToDatabaseColumn(Boolean value) 
    {
        if(value)
          return 1;
        else
          return 0;
    }
    
    @Override
    public Boolean convertToEntityAttribute(Integer value) 
    {
        return value != null && value.equals(1);
    }
}
