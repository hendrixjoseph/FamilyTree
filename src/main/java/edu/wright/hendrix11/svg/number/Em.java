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
public class Em<N extends Number & Comparable<N>> extends Unit<N>
{

    /**
     *
     */
    public static final Em<Double> HALF = new Em<>(0.5);

    /**
     *
     */
    public static final Em<Integer> ONE = new Em<>(1);

    /**
     * @param number
     */
    public Em(N number)
    {
        super(number, "em");
    }

    /**
     * @param d
     *
     * @return
     */
    @Override
    public Em<Double> add(double d)
    {
        return new Em<>(number.doubleValue() + d);
    }

    /**
     * @param i
     *
     * @return
     */
    @Override
    public Em<Integer> add(int i)
    {
        return new Em<>(number.intValue() + i);
    }

    /**
     * @param d
     *
     * @return
     */
    @Override
    public Em<Double> divide(double d)
    {
        return new Em<>(number.doubleValue() / d);
    }

    /**
     * @param i
     *
     * @return
     */
    @Override
    public Em<Integer> divide(int i)
    {
        return new Em<>(number.intValue() / i);
    }

    /**
     * @param d
     *
     * @return
     */
    @Override
    public Em<Double> multiply(double d)
    {
        return new Em<>(number.doubleValue() * d);
    }

    /**
     * @param i
     *
     * @return
     */
    @Override
    public Em<Integer> multiply(int i)
    {
        return new Em<>(number.intValue() * i);
    }
}
