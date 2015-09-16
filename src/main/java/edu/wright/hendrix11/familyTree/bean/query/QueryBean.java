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
 */
package edu.wright.hendrix11.familyTree.bean.query;

import edu.wright.hendrix11.familyTree.entity.Person;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public abstract class QueryBean
{

    /**
     *
     * @param actionEvent
     */
    public abstract void commit(ActionEvent actionEvent);
    
    /**
     *
     * @param person
     * @return
     */
    protected String getAction(Person person)
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("index?personId=");
        sb.append(person.getId());
        sb.append("&amp;").append(getRedirectAction());
        
        return sb.toString();
    }
    
    /**
     *
     * @return
     */
    protected String getRedirectAction()
    {
        return "faces-redirect=true";
    }
    
    /**
     *
     * @return
     */
    public abstract String getAction();
}
