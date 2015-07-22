package theme;


import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author QQJ23707
 */
@ManagedBean
@ApplicationScoped
public class ThemeChoices
{
    private List<String> themes;// = {"No themes loaded"};
    
    private String currentTheme;
    
    @PostConstruct
    public void initialize()
    {
        String path = this.getClass().getResource("").getPath();
        
        int index = path.indexOf("WEB-INF/") + "WEB-INF/".length();
        path = path.substring(0, index);
        path = path + "lib/";
        
        String jarName = path + "all-themes-1.0.10.jar";
        
        ThemeParser themeParser = new ThemeParser(jarName);
        
        if(themeParser.getThemes().size() > 0)
        {
            themes = themeParser.getThemes();
            
            String defaultTheme = getDefaultTheme();
            
            if(themes.contains(defaultTheme))
            {
                themes.remove(defaultTheme);
            }
            
            themes.add(0, defaultTheme);
        }
    }
    
    public List<String> getThemes()
    {
        return themes;
    }
    
    public void setCurrentTheme(String theme)
    {
      currentTheme = theme;
    }
    
    public String getCurrentTheme()
    {
      return currentTheme;
    }
}
