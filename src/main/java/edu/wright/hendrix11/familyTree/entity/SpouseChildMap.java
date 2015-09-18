/* 
 *  The MIT License (MIT)
 *  
 *  Copyright (c) 2015 Joseph Hendrix
 *  
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *  
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *  
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *  
 *  Hosted on GitHub at https://github.com/hendrixjoseph/FamilyTree
 *  
 */
package edu.wright.hendrix11.familyTree.entity;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class SpouseChildMap
{
    PersonView person;
    PersonView spouse;
    PersonView child;
    
    /**
     *
     */
    public SpouseChildMap()
    {
        person = new PersonView();
        spouse = null;
        child = null;
    }

    /**
     *
     * @return
     */
    public PersonView getPerson()
    {
        return person;
    }

    /**
     *
     * @param person
     */
    public void setPerson(PersonView person)
    {
        this.person = person;
    }

    /**
     *
     * @return
     */
    public PersonView getSpouse()
    {
        if(spouse == null)
            spouse = new PersonView();
        
        return spouse;
    }

    /**
     *
     * @param spouse
     */
    public void setSpouse(PersonView spouse)
    {
        this.spouse = spouse;
    }

    /**
     *
     * @return
     */
    public PersonView getChild()
    {
        if(child == null)
            child = new PersonView();
            
        return child;
    }

    /**
     *
     * @param child
     */
    public void setChild(PersonView child)
    {
        this.child = child;
    }
    
    /**
     *
     * @param person
     * @param spouse
     * @param child
     */
    public void setPeople(PersonView person, PersonView spouse, PersonView child)
    {
        this.person = person;
        this.spouse = spouse;
        this.child = child;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("person:\n").append(person.toString("\t"));
        
        sb.append("\nspouse:\n").append(spouse == null ? "\t(null)" : spouse.toString("\t"));
        
        sb.append("\nchild:\n").append(child == null ? "\t(null)" : child.toString("\t"));
        
        
        return sb.toString();
    }
}
