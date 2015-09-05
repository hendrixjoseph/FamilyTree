/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseTests.NonTestClasses;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public interface TestData<O>
{
    public void outputInsert(O o);
    public void outputUpdate(O o);
}
