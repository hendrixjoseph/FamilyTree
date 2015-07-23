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

    public String getTheme()
    {
        return theme;
    }

    public void setTheme(String theme)
    {
        this.theme = theme;
    }

    public Person getDefaultPerson()
    {
        return defaultPerson;
    }

    public void setDefaultPerson(Person defaultPerson)
    {
        this.defaultPerson = defaultPerson;
    }

    public String getDefaultPersonType()
    {
        return defaultPersonType;
    }

    public void setDefaultPersonType(String defaultPersonType)
    {
        this.defaultPersonType = defaultPersonType;
    }

    public boolean isViewWelcomePage()
    {
        return viewWelcomePage;
    }

    public void setViewWelcomePage(boolean viewWelcomePage)
    {
        this.viewWelcomePage = viewWelcomePage;
    }
}
