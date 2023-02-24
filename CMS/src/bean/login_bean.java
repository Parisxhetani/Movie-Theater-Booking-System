package bean;

import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.play_model;
import models.show_model;

import java.io.IOException;
import java.util.List;

@Named("login_bean")
public class login_bean
{
    private String username;
    private String password;

    public void login()
    {

        String baseUrl = "http://localhost:8080/cms/login?username=" + username + "&pass=" + password;

        Client client = ClientBuilder.newClient();
        Response response = client.target(baseUrl)
                .request(MediaType.APPLICATION_JSON)
                .get();

        // Check if the request was successful (HTTP status code 200-299)
        if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
            boolean res = response.readEntity(new GenericType<Boolean>()
            {
            });

            if (res == true) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("localhost:8080/cms/");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.err.println(response.getStatus() + ": " + response.getStatusInfo().getReasonPhrase());
        }
        client.close();
    }

    // Getters and setters for the username and password properties
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
