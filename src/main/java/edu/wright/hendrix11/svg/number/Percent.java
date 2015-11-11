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
public class Percent<N extends Number> extends Unit<N>
{
    public static final Percent<Double> FIFTY = new Percent<>(50.0);
    public static final Percent<Double> ONE_HUNDRED = new Percent<>(100.0);
    public static final Percent<Double> ZERO = new Percent<>(0.0);

    public Percent(N number)
    {
        super(number, "%");
    }

    @Override
    public Percent<Double> add(double d)
    {
        return new Percent<Double>(number.doubleValue() + d);
    }

    @Override
    public Percent<Integer> add(int i)
    {
        return new Percent<Integer>(number.intValue() + i);
    }

    @Override
    public Percent<Double> subtract(double d)
    {
        return new Percent<Double>(number.doubleValue() - d);
    }

    @Override
    public Percent<Integer> subtract(int i)
    {
        return new Percent<Integer>(number.intValue() - i);
    }

    @Override
    public Percent<Double> divide(double d)
    {
        return new Percent<Double>(number.doubleValue() / d);
    }

    @Override
    public Percent<Integer> divide(int i)
    {
        return new Percent<Integer>(number.intValue() / i);
    }
}
