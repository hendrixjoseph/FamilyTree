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

import javax.faces.context.ResponseWriter;

import java.io.IOException;

/**
 * @author Joe Hendrix
 */
public abstract class PolyShape extends SvgComponent
{
    List<Number> points = new ArrayList<>();

    protected RoundShape(String name)
    {
        super(name);
    }
    
    public void setPoints(List<Number> points)
    {
        this.points = points;
    }
    
    public void addPoint(Number point)
    {
        points.add(point);
    }

    private void processPoints()
    {
      StringBuilder sb = new StringBuilder();
      
      
    }
}
