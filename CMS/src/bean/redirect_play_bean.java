package bean;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@Named("redirect_play_bean")
@RequestScoped
public class redirect_play_bean
{
    public redirect_play_bean()
    {

    }

    public void getPlayId()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String id = request.getParameter("id");

        // Construct the URL for the target page and add the id parameter
        String url = "shows.xhtml?id=" + id;

        // Redirect to the target page
        try
        {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void toLogin()
    {
        String url = "login.xhtml";
        try
        {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void toSignup() {

        String url = "login.xhtml";
        try
        {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
