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

package edu.wright.hendrix11.svg.component.shape;

import edu.wright.hendrix11.svg.component.SvgComponent;

import javax.faces.context.ResponseWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Joe Hendrix
 */
public abstract class PolyShape extends SvgComponent
{
    List<Point> points = new ArrayList<>();

    protected PolyShape(String name)
    {
        super(name);
    }

    public void setPoints(List<Point> points)
    {
        this.points = points;
        processPoints();
    }

    public void addPoint(Point point)
    {
        points.add(point);
        processPoints();
    }

    @Override
    public void encodeMiddle(ResponseWriter writer) throws IOException
    {

    }

    private void processPoints()
    {
        StringBuilder sb = new StringBuilder();

        for ( Point point : points )
        {
            sb.append(point).append(" ");
        }

        sb.setLength(sb.length() - 1);

        putAttribute("points", sb.toString());
    }

    public class Point
    {
        private Number x;
        private Number y;

        public Point(Number x, Number y)
        {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString()
        {
            return x + "," + y;
        }
    }
}
