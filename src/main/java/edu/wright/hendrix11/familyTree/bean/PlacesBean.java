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
package edu.wright.hendrix11.familyTree.bean;

import edu.wright.hendrix11.familyTree.database.table.PlaceTable;
import edu.wright.hendrix11.familyTree.entity.Place;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
*
* @author Joe Hendrix
*/
@ManagedBean
@ViewScoped
public class PlacesBean extends DataBean<Place>
{
    private static final String[] columns = {"ID","NAME"};
    private static final String[] prettyNames = {"Id","Name"};

    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
        table = new PlaceTable();
        
        super.initialize(table);
    }
    
    /**
     *
     * @return
     */
    @Override
    public String[] getPrettyNames()
    {
      return prettyNames;
    }
    
    /**
     *
     * @return
     */
    @Override
    protected String[] getColumns()
    {
        return columns;
    }
    
//    @Override
//    protected String processLink(String column, String value)
//    {
//        return "";
//    }
}