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
package edu.wright.hendrix11.familyTree.database.table;

import edu.wright.hendrix11.familyTree.database.ColumnMethodMap;
import edu.wright.hendrix11.familyTree.database.DatabaseQuery;
import edu.wright.hendrix11.familyTree.database.interfaces.SelectAllData;
import edu.wright.hendrix11.familyTree.entity.Gender;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
*
* @author Joe Hendrix <hendrix.11@wright.edu>
*/
public class GenderTable extends DatabaseQuery implements SelectAllData<Gender>
{

    /**
     *
     */
    public GenderTable()
    {
        super("GENDER", Gender.class);

        ColumnMethodMap columnMethodMap = this.getColumnMethodMap();

        columnMethodMap.setPrimaryKey("ABBR");
    }

    @Override
    public List<Gender> selectAll()
    {
        List<Gender> genders = new ArrayList<Gender>();
    
        List<Object> objects = super.selectAllObjects();
        
        for(Object object : objects)
        {
            if(object instanceof Gender)
                genders.add((Gender)object);
        }
        
        return genders;
    }
    
    /**
     *
     * @return
     */
    @Override
    protected Object getNew()
    {
        return new Gender();
    }
}
