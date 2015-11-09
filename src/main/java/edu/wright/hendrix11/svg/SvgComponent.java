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

package edu.wright.hendrix11.svg;

/**
 * @author Joe Hendrix
 */
public abstract class SvgComponent extends SuperSvgClass
{
    protected SvgComponent(String name)
    {
        super(name);
    }

    public void setTransform(Transform transform)
    {
        put("transform",transform);
    }

    public Transform getTransform()
    {
        return (Transform)get("transform");
    }
}
