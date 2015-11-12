/*
 *  The MIT License (MIT)
 *
 *  View the full license at:
 *  https://github.com/hendrixjoseph/FamilyTree/blob/master/LICENSE.md
 *
 *  Copyright (c) 2015 Joseph Hendrix
 *
 *  Hosted on GitHub at https://github.com/hendrixjoseph/FamilyTree
 *
 */

package edu.wright.hendrix11.svg.number;

/**
 * @author Joe Hendrix
 */
public class Unit<N extends Number & Comparable> extends Number
{
    N number;
    String unit;

    public Unit(N number, String unit)
    {
        this(unit);
        this.number = number;
    }

    public Unit(String unit)
    {
        this.unit = unit;
    }

    public Unit<Double> subtract(double d)
    {
        return new Unit<Double>(number.doubleValue() - d, unit);
    }

    public Unit<Integer> subtract(int i)
    {
        return new Unit<Integer>(number.intValue() - i, unit);
    }

    public Unit<Double> add(double d)
    {
        return new Unit<Double>(number.doubleValue() + d, unit);
    }

    public Unit<Integer> add(int i)
    {
        return new Unit<Integer>(number.intValue() + i, unit);
    }

    public Unit<Double> divide(double d)
    {
        return new Unit<Double>(number.doubleValue() / d, unit);
    }

    public Unit<Integer> divide(int i)
    {
        return new Unit<Integer>(number.intValue() / i, unit);
    }
    
    public Unit<Double> multiply(double d)
    {
        return new Unit<Double>(number.doubleValue() * d, unit);
    }

    public Unit<Integer> multiply(int i)
    {
        return new Unit<Integer>(number.intValue() * i, unit);
    }

    public boolean lessThan(Number n)
    {
        return number.compareTo(n) < 0;
    }
    
    public boolean greaterThan(Number n)
    {
        return number.compareTo(n) > 0;
    }

    public N getNumber()
    {
        return number;
    }

    @Override
    public int intValue()
    {
        return number.intValue();
    }

    @Override
    public long longValue()
    {
        return number.longValue();
    }

    @Override
    public float floatValue()
    {
        return number.floatValue();
    }

    @Override
    public double doubleValue()
    {
        return number.doubleValue();
    }

    @Override
    public int hashCode()
    {
        return number.hashCode();
    }

    @Override
    public boolean equals(Object object)
    {
        if ( object instanceof Unit )
        {
            Unit other = (Unit) object;
            return number.equals(other.getNumber()) && unit.equals(other.unit);
        }
        else if ( object instanceof Number )
        {
            Number other = (Number) object;
            return number.equals(other);
        }
        else
        {
            return false;
        }
    }

    @Override
    public String toString()
    {
        return number + unit;
    }
}
