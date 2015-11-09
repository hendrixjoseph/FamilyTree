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
public class Matrix extends Transform
{
    private Number a;
    private Number b;
    private Number c;
    private Number d;
    private Number e;
    private Number f;
    
    public Matrix(Number a, Number b, Number c, Number d, Number e, Number f)
    {
      this.a = a != null ? a : 0;
      this.b = b != null ? b : 0;
      this.c = c != null ? c : 0;
      this.d = d != null ? d : 0;
      this.e = e != null ? e : 0;
      this.f = f != null ? f : 0;
    }
    
    @Override
    public String toString()
    {
      Number[] args = {a,b,c,d,e,f};
      
      return toString("matrix",args);
    }
}
