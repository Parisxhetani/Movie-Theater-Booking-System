package bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.faces.component.UIData;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.play_model;

import java.io.Serializable;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Named("play_bean")
@ViewScoped
public class play_bean implements Serializable
{
    private String searchTerm;

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    private List<play_model> list_of_plays;

    public void filterData() {
        // Get the array list of plays to be filtered
        List<play_model> myPlays = getPlays();

        // Create a new list to hold the filtered plays
        List<play_model> filteredPlays = new ArrayList<>();

        // Loop through the plays and check if each one contains the search term
        for (play_model play : myPlays) {
            if (play.getTitle().contains(searchTerm) || play.getWriter().contains(searchTerm) || play.getDirector().contains(searchTerm) || play.getActors().contains(searchTerm)) {
                filteredPlays.add(play);
            }
        }

        // Update the array list of plays to be displayed
        setPlays(filteredPlays);

    }


    public play_bean() throws SQLException
    {
        list_of_plays = new ArrayList<>();

        // Create a client object to make HTTP requests
        Client client = ClientBuilder.newClient();

        // Set the base URL for the API
        String baseUrl = "http://localhost:8080/cms/plays";

        // Send a GET request to retrieve all plays
        Response response = client.target(baseUrl)
                .request(MediaType.APPLICATION_JSON)
                .get();

        // Check if the request was successful (HTTP status code 200-299)
        if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
            // Get the response entity (list of plays) as a List<play_model>
            list_of_plays = response.readEntity(new GenericType<List<play_model>>()
            {

            });
        } else {
            System.err.println(response.getStatus() + ": " + response.getStatusInfo().getReasonPhrase());
        }
        client.close();
    }

    public List<play_model> getPlays()
    {
        return list_of_plays;
    }

    public void setPlays(List<play_model> list_of_plays)
    {
        this.list_of_plays = list_of_plays;
    }


}






