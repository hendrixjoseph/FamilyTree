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
package edu.wright.hendrix11.familyTree.bean;

import javax.faces.bean.ManagedBean;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@ManagedBean
public class ImportBean
{

    private UploadedFile file;

    /**
     *
     * @return
     */
    public UploadedFile getFile()
    {
        return file;
    }

    /**
     *
     * @param file
     */
    public void setFile(UploadedFile file)
    {
        this.file = file;
    }

    /**
     *
     */
    public void upload()
    {
        if (file != null)
        {
            // Do something
        }
    }
}
