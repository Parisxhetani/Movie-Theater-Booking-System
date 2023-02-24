package bean;

import dao.show_dao;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.play_model;
import models.show_model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Named("show_bean")
@ViewScoped
public class show_bean implements Serializable
{
    private List<show_model> list_of_shows;

    public List<show_model> getShows()
    {
        return list_of_shows;
    }

    public void setShows(List<show_model> list_of_shows)
    {
        this.list_of_shows = list_of_shows;
    }

    private String searchTerm;

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }


    public void filterData() {
        // Get the array list of plays to be filtered
        List<show_model> myShows = getShows();

        // Create a new list to hold the filtered plays
        List<show_model> filteredShows = new ArrayList<>();

        // Loop through the plays and check if each one contains the search term
        for (show_model show : myShows) {
            if (show.getShowDate().contains(searchTerm) || show.getHallName().contains(searchTerm) || show.getShowTime().contains(searchTerm) || show.getPlayTitle().contains(searchTerm) ) {
                filteredShows.add(show);
            }
        }
        /*
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        String idParam = externalContext.getRequestParameterMap().get("param.id");
        setShows(filteredShows);
*/
    }



    public show_bean() {

    }

    public List<show_model> get_show_by_id(String play_id) throws SQLException
    {
        list_of_shows = new ArrayList<>();

        // Create a client object to make HTTP requests
        Client client = ClientBuilder.newClient();

        // Set the base URL for the API
        String baseUrl = "http://localhost:8080/cms/shows?play_id=" + play_id;

        // Send a GET request to retrieve all plays
        Response response = client.target(baseUrl)
                .request(MediaType.APPLICATION_JSON)
                .get();

        // Check if the request was successful (HTTP status code 200-299)
        if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
            // Get the response entity (list of plays) as a List<play_model>
            list_of_shows = response.readEntity(new GenericType<List<show_model>>()
            {
            });
        } else {
            System.err.println(response.getStatus() + ": " + response.getStatusInfo().getReasonPhrase());
        }
        client.close();
        return list_of_shows;
    }
}
