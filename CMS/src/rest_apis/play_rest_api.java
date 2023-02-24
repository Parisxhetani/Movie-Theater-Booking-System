package rest_apis;

import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;
import dao.play_dao;
import jakarta.servlet.ServletException;
import models.play_model;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class play_rest_api extends HttpServlet
{
    @Inject
    private play_dao playDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try
        {
            List<play_model> plays = playDAO.getAllPlays();
            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(plays));
        }
        catch (Exception e)
        {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error: " + e.getMessage());
        }
    }
}
