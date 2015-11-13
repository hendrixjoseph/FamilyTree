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
 * @param <N>
 *
 * @author Joe Hendrix
 */
public class Unit<N extends Number & Comparable> extends Number
{
    N number;
    String unit;

    /**
     * @param number
     * @param unit
     */
    public Unit(N number, String unit)
    {
        this(unit);
        this.number = number;
    }

    /**
     * @param unit
     */
    public Unit(String unit)
    {
        this.unit = unit;
    }

    /**
     * @param d
     *
     * @return
     */
    public Unit<Double> subtract(double d)
    {
        return new Unit<>(number.doubleValue() - d, unit);
    }

    /**
     * @param i
     *
     * @return
     */
    public Unit<Integer> subtract(int i)
    {
        return new Unit<>(number.intValue() - i, unit);
    }

    /**
     * @param d
     *
     * @return
     */
    public Unit<Double> add(double d)
    {
        return new Unit<>(number.doubleValue() + d, unit);
    }

    /**
     * @param i
     *
     * @return
     */
    public Unit<Integer> add(int i)
    {
        return new Unit<>(number.intValue() + i, unit);
    }

    /**
     * @param d
     *
     * @return
     */
    public Unit<Double> divide(double d)
    {
        return new Unit<>(number.doubleValue() / d, unit);
    }

    /**
     * @param i
     *
     * @return
     */
    public Unit<Integer> divide(int i)
    {
        return new Unit<>(number.intValue() / i, unit);
    }

    /**
     * @param d
     *
     * @return
     */
    public Unit<Double> multiply(double d)
    {
        return new Unit<>(number.doubleValue() * d, unit);
    }

    /**
     * @param i
     *
     * @return
     */
    public Unit<Integer> multiply(int i)
    {
        return new Unit<>(number.intValue() * i, unit);
    }

    /**
     * @param n
     *
     * @return
     */
    public boolean lessThan(Number n)
    {
        return number.compareTo(n) < 0;
    }

    /**
     * @param n
     *
     * @return
     */
    public boolean greaterThan(Number n)
    {
        return number.compareTo(n) > 0;
    }

    /**
     * @return
     */
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
            Unit<?> other = (Unit<?>) object;
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
