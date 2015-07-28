package edu.wright.hendrix11.familyTree.bean;


import edu.wright.hendrix11.theme.ThemeParser;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author QQJ23707
 */
@ManagedBean
@ApplicationScoped
public class ThemesBean
{
    private List<String> themes;// = {"No themes loaded"};
    
    private String currentTheme;
    
    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
        String path = this.getClass().getResource("").getPath();
        
        int index = path.indexOf("WEB-INF/") + "WEB-INF/".length();
        path = path.substring(0, index);
        path = path + "lib/";
        
        String jarName = path + "all-themes-1.0.10.jar";
        
        ThemeParser themeParser = new ThemeParser(jarName);
        
        themes = themeParser.getThemes();
    }
    
    /**
     *
     * @return
     */
    public List<String> getThemes()
    {
        return themes;
    }
    
    /**
     *
     * @param theme
     */
    public void setCurrentTheme(String theme)
    {
      currentTheme = theme;
    }
    
    /**
     *
     * @return
     */
    public String getCurrentTheme()
    {
      return currentTheme;
    }
}
