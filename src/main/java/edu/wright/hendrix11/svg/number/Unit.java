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
public class Unit<N extends Number> extends Number
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

    public N getNumber()
    {
        return number;
    }

    public void setNumber(N number)
    {
        this.number = number;
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
    public boolean equals(Object obj)
    {
        if ( obj instanceof Unit )
        {
            Unit other = (Unit) obj;
            return number.equals(other.getNumber()) && unit.equals(other.unit);
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
