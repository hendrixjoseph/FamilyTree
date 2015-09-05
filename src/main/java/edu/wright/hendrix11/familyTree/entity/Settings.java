package edu.wright.hendrix11.familyTree.entity;

/**
*
* @author Joe Hendrix
*/
public class Settings
{
  String theme;
  Person defaultPerson;
  String defaultPersonType;
  boolean viewWelcomePage;

    /**
     *
     * @return
     */
    public String getTheme()
    {
        return theme;
    }

    /**
     *
     * @param theme
     */
    public void setTheme(String theme)
    {
        this.theme = theme;
    }

    /**
     *
     * @return
     */
    public Person getDefaultPerson()
    {
        return defaultPerson;
    }

    /**
     *
     * @param defaultPerson
     */
    public void setDefaultPerson(Person defaultPerson)
    {
        this.defaultPerson = defaultPerson;
    }

    /**
     *
     * @return
     */
    public String getDefaultPersonType()
    {
        return defaultPersonType;
    }

    /**
     *
     * @param defaultPersonType
     */
    public void setDefaultPersonType(String defaultPersonType)
    {
        this.defaultPersonType = defaultPersonType;
    }

    /**
     *
     * @return
     */
    public boolean isViewWelcomePage()
    {
        return viewWelcomePage;
    }

    /**
     *
     * @param viewWelcomePage
     */
    public void setViewWelcomePage(boolean viewWelcomePage)
    {
        this.viewWelcomePage = viewWelcomePage;
    }
}
