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
public class Em<N extends Number & Comparable> extends Unit<N>
{
    public static final Em<Double> HALF = new Em<>(0.5);
    public static final Em<Integer> ONE = new Em<>(1);

    public Em(N number)
    {
        super(number, "em");
    }

    @Override
    public Em<Double> add(double d)
    {
        return new Em<Double>(number.doubleValue() + d);
    }

    @Override
    public Em<Integer> add(int i)
    {
        return new Em<Integer>(number.intValue() + i);
    }

    @Override
    public Em<Double> divide(double d)
    {
        return new Em<Double>(number.doubleValue() / d);
    }

    @Override
    public Em<Integer> divide(int i)
    {
        return new Em<Integer>(number.intValue() / i);
    }
    
    @Override    
    public Em<Double> multiply(double d)
    {
        return new Em<Double>(number.doubleValue() * d;
    }

    @Override
    public Em<Integer> multiply(int i)
    {
        return new Em<Integer>(number.intValue() * i);
    }
}
