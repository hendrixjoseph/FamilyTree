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

package edu.wright.hendrix11.svg.transform;

/**
 * @author Joe Hendrix
 */
public abstract class Transform
{
  protected String toString(String name, Number[] arguments)
  {
    StringBuilder sb = new StringBuilder(name).append("(");
    
    for(Number argument : arguments)
    {
      sb.append(argument).append(",");
    }
    
    sb.setLength(sb.length() - 1);
    
    return sb.append(")").toString();
  }
}
