package rest_apis;

import com.google.gson.Gson;
import dao.play_dao;
import dao.show_dao;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.play_model;
import models.show_model;

import java.io.IOException;
import java.util.List;

public class show_rest_api extends HttpServlet
{
    @Inject
    private show_dao show_dao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            String play_id = request.getParameter("play_id");
            List<show_model> plays = show_dao.get_shows_by_play_id(play_id);
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
